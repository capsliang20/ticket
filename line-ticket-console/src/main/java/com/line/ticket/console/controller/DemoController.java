package com.line.ticket.console.controller;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.entity.Demo;
import com.line.ticket.common.entity.generic.Result;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    DemoService demoService;

    @RequestMapping(value = "/selectDemo", method = RequestMethod.GET)
    public Result demo(@Param("id") Integer id) {
        Demo result = demoService.selectDemo(id);
        Demo result2 = demoService.selectDemo(id);
        Demo result3 = demoService.selectDemo(id);
        List<Demo> list = List.of(result, result2, result3);
        return Result.success(list);
    }
}
