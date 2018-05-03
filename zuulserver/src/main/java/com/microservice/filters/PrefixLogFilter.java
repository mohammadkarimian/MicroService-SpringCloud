package com.microservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PrefixLogFilter extends ZuulFilter {
    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 1;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            InputStream in  = (InputStream) context.get("requestEntity");
            if (in == null){
                in = context.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));

            System.out.println("Prefix Filter : " + body);

            final byte[] bytes = body.getBytes("UTF-8");
            context.setRequest(new HttpServletRequestWrapper(RequestContext.getCurrentContext().getRequest()) {
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    return new ServletInputStreamWrapper(bytes);
                }

                @Override
                public int getContentLength() {
                    return bytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return bytes.length;
                }
            });
        }catch (IOException e){
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}