package com.line.ticket.console.controller;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.entity.Demo;
import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.util.HttpTool;
import com.line.ticket.common.util.JSON;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
@Log4j2
public class DemoController {

    @Resource
    DemoService demoService;

    @RequestMapping(value = "/selectDemo", method = RequestMethod.GET)
    public Result demo(@Param("id") Integer id) throws IOException {
        System.out.println("invoke");
        HttpTool.get("www.baidu.com");
        log.info("xxx");
        Demo result = demoService.selectDemo(id);
        Demo result2 = demoService.selectDemo(id);
        Demo result3 = demoService.selectDemo(id);
        List<Demo> list = List.of(result, result2, result3);
        return Result.success(list);
    }

    @RequestMapping(value = "bodyTest")
    public Result<Demo> bodyTest(@RequestBody Demo demo) {
        System.out.println(JSON.toJSONString(demo));
        return Result.success(demo);
    }

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) {
        Map<String, Object> map = new HashedMap<>();
        log.info("request.getContextPath:{}", request.getContextPath());
        request.getHeaderNames().asIterator().forEachRemaining(string -> log.info("request.getHeaderNames:{}", string));
        log.info("request.getMethod:{}", request.getMethod());
        log.info("request.getQueryString:{}", request.getQueryString());
        log.info("request.getRequestURI:{}", request.getRequestURI());
        log.info("request.getRequestURL:{}", request.getRequestURL());
        for (Cookie cookie : request.getCookies()) {
            log.info("request.getCookies:{}", cookie.toString());
        }
        log.info("request.getServletPath:{}", request.getServletPath());
        log.info("request.getSession:{}", request.getSession().toString());
        request.getAttributeNames().asIterator().forEachRemaining(attributeName -> log.info("request.getAttributeNames:{}", attributeName));
        log.info("request.getCharacterEncoding:{}", request.getCharacterEncoding());
        log.info("request.getContentLength:{}", request.getContentLength());
        log.info("request.getContentType:{}", request.getContentType());
        request.getParameterNames().asIterator().forEachRemaining(parameterName -> log.info("request.getParameterNames:{}", parameterName));

        try {
            String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            log.info("request.getBody:{}", body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString("");
    }
}
