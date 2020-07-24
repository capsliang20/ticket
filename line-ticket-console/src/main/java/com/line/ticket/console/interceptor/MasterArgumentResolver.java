package com.line.ticket.console.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.ticket.common.entity.generic.BodyRequest;
import com.line.ticket.common.entity.generic.Request;
import com.line.ticket.common.util.BeanTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
@Log4j2
public class MasterArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("MasterArgumentResolver.supportsParameter()");
        return Request.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Object request = BeanTool.buildRequest(httpRequest, parameter.getParameterType());
        ObjectMapper objectMapper = new ObjectMapper();


        ServletInputStream in = httpRequest.getInputStream();
        ParameterizedType parameterizedType= (ParameterizedType) parameter.getParameter().getParameterizedType();
        if (in.available() > 0 && BodyRequest.class.isAssignableFrom(parameter.getParameterType())) {
            BodyRequest bodyRequest = (BodyRequest) request;
        }
        return null;
    }
}
