package com.line.ticket.console.controller;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.util.JSON;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    DemoService demoService;

    @RequestMapping(value = "/selectDemo", method = RequestMethod.GET)
    public String demo(@Param("id") Integer id) {
        return JSON.toJSONString(demoService.selectDemo(id));
    }
}
