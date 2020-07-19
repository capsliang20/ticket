package com.line.ticket.console.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private final MasterInterceptor masterInterceptor;

    public InterceptorConfig(MasterInterceptor masterInterceptor) {
        this.masterInterceptor = masterInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(masterInterceptor).addPathPatterns("/**");
    }
}
