package com.microservice;

import com.microservice.filters.PostfixLogFilter;
import com.microservice.filters.PrefixLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulConfig {

    @Bean
    public PostfixLogFilter postfixLogFilter(){
        return new PostfixLogFilter();
    }

    @Bean
    public PrefixLogFilter prefixLogFilter(){
        return new PrefixLogFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulConfig.class, args);
    }
}