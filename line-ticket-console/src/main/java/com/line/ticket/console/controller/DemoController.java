package com.line.ticket.console.controller;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.entity.Demo;
import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.util.HttpTool;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
}
