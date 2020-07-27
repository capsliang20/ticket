package com.line.ticket.console.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private final MasterInterceptor masterInterceptor;

    private final MasterArgumentResolver masterArgumentResolver;

    public WebConfig(MasterInterceptor masterInterceptor, MasterArgumentResolver masterArgumentResolver) {
        this.masterInterceptor = masterInterceptor;
        this.masterArgumentResolver = masterArgumentResolver;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(masterInterceptor).addPathPatterns("/**");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(masterArgumentResolver);
    }
}
