package com.line.ticket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.line.ticket.common.entity.generic.BodyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanTool {

    private static final Map<Field, JavaType> commonTypesCache = new ConcurrentHashMap<>(32);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final TypeFactory typeFactory = TypeFactory.defaultInstance();

    private static final GenericConversionService conversionService = new DefaultConversionService();

    public static <T> T buildRequest(HttpServletRequest request, Class<? extends T> clazz) {
        T obj;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("BeanTool.newBean error. parameterMap:{},clazz:{}", request.getParameterMap(), clazz, e);
            throw new UnsupportedOperationException(e);
        }
        for (Field field : clazz.getDeclaredFields()) {
            String value = request.getParameter(field.getName());
            if (value == null || !field.trySetAccessible())
                continue;
            Class<?> type = field.getType();
            try {
                if (String.class.isAssignableFrom(type))
                    field.set(obj, value);
                else if (!conversionService.canConvert(String.class, type) || type.isArray() || Collection.class.isAssignableFrom(type)) {
                    JavaType javaType = commonTypesCache.get(field);
                    if (javaType == null) {
                        javaType = typeFactory.constructType(field.getGenericType());
                        commonTypesCache.put(field, javaType);
                    }
                    field.set(obj, objectMapper.readValue(value, javaType));
                } else
                    field.set(obj, conversionService.convert(value, type));
            } catch (IllegalAccessException | JsonProcessingException ignored) {
                log.warn("BeanTool.newBean error. set field:{}, value:{}", field.getName(), value);
            }
        }
        if (BodyRequest.class.isAssignableFrom(clazz)) {
            try {
                ServletInputStream in = request.getInputStream();
                //必须继承类似AbstractBodyRequest这种类 改进待续
                if (in.available() != 0) {
                    Object body = objectMapper.readValue(in.readAllBytes(), typeFactory.constructType(((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]));
                    ((BodyRequest) obj).setBody(body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
