package com.line.ticket.service;

import com.line.ticket.common.api.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LineTicketServiceApplicationTests {

    @Autowired
    DemoService demoService;

    @Test
    void contextLoads() {
        System.out.println(demoService.selectDemo(1));
    }

}
