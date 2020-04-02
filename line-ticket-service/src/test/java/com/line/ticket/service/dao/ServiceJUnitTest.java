package com.line.ticket.service.dao;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.service.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:test.properties")
@Slf4j
public class ServiceJUnitTest {
    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void serviceJUnitCase() {
        log.info("demoService:{}", demoService.selectDemo(1));
        log.info("demoMapper:{}", demoMapper.selectDemo(1));
    }
}
