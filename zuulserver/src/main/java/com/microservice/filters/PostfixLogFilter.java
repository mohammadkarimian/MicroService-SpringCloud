package com.microservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PostfixLogFilter extends ZuulFilter {
    public String filterType() {
        return "post";
    }

    public int filterOrder() {
        return 2;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            System.out.println("PostFix Filter : " + body);
            context.setResponseBody(body);
        }catch (IOException e){
            ReflectionUtils.rethrowRuntimeException(e);
        }

        return null;
    }
}
