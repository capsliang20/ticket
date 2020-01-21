package com.line.ticket.service;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.util.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LineTicketServiceApplicationTests {

    @Autowired
    DemoService demoService;

    @Test
    void contextLoads() {
        log.info("result:{}", JSON.toJSONString(demoService.selectDemo(1)));
    }

}
