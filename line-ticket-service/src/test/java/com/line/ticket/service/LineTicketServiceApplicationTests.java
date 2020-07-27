package com.line.ticket.service;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.api.TicketService;
import com.line.ticket.common.api.UserService;
import com.line.ticket.service.mapper.DemoMapper;
import com.line.ticket.service.mapper.TicketMapper;
import com.line.ticket.service.mapper.TicketOrderMapper;
import com.line.ticket.service.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Log4j2
class LineTicketServiceApplicationTests {

    @Autowired
    private DemoService demoService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private TicketOrderMapper orderMapper;

    @Resource
    private DemoMapper demoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
//        log.info("ticketService selectTicketDetail:{}", ticketService.getTicketDetail(1));
//        log.info("ticketService buyTicket:{}", ticketService.buyTicket(1, 1));
//        log.info("ticketService refundTicket:{}", ticketService.refundTicket(3, 1));
        log.info("get:{}",demoService.selectDemo(1));
    }
}
