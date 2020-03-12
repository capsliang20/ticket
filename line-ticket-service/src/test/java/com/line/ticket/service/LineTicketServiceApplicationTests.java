package com.line.ticket.service;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.entity.Ticket;
import com.line.ticket.common.entity.User;
import com.line.ticket.common.util.JSON;
import com.line.ticket.service.mapper.TicketMapper;
import com.line.ticket.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class LineTicketServiceApplicationTests {

    @Autowired
    private DemoService demoService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TicketMapper ticketMapper;

    @Test
    void contextLoads() {
        Integer id = userMapper.verifyLogin("qwwaq@qq.com", "liang1998");
        log.info("correct login :{}", id);
        User user = userMapper.getUserLoginInfo(id);
        log.info("login user info :{}", JSON.toJSONString(user));
        Ticket ticket = ticketMapper.getTicketInfo(1);
        log.info("ticket info: {}", JSON.toJSONString(ticket));
    }
}
