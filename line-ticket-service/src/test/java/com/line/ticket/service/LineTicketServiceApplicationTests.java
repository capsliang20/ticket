package com.line.ticket.service;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.api.TicketService;
import com.line.ticket.common.api.UserService;
import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.entity.service.User;
import com.line.ticket.common.util.JSON;
import com.line.ticket.service.mapper.DemoMapper;
import com.line.ticket.service.mapper.TicketMapper;
import com.line.ticket.service.mapper.TicketOrderMapper;
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

    @Resource
    private TicketOrderMapper orderMapper;

    @Resource
    private DemoMapper demoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Test
    void contextLoads() {
        Result<User> user = userService.login("qwwaq@qq.com", "liang1998");
        log.debug("user: {}", JSON.toJSONString(user));
    }
}
