package com.line.ticket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.line.ticket.common.entity.exception.BaseException;
import com.line.ticket.common.entity.generic.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanTool {

    private static final Map<Class<?>, JavaType> commonTypesCache = new ConcurrentHashMap<>(32);

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
                        typeFactory.constructType(field.getGenericType());
                    }
                    field.set(obj, objectMapper.readValue(value, typeFactory.constructType(field.getGenericType())));

                } else
                    field.set(obj, conversionService.convert(value, type));
            } catch (IllegalAccessException | JsonProcessingException ignored) {
                log.warn("BeanTool.newBean error. set field:{}, value:{}", field.getName(), value);
            }
        }
        return obj;
    }

}
