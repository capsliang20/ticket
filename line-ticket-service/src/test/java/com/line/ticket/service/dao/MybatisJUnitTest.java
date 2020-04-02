package com.line.ticket.service.dao;

import com.line.ticket.common.entity.Demo;
import com.line.ticket.common.util.JSON;
import com.line.ticket.service.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
@Slf4j
public class MybatisJUnitTest {
    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void findById() {
        Demo demo = demoMapper.selectDemo(1);
        log.info("demo result: {}", JSON.toJSONString(demo));
//        demo.setId(null);
//        demo.setName("name");
//        log.info("demo insert: {}", demoMapper.insertDemo(demo));
//        log.info("demo after insert: {}",JSON.toJSONString(demo));
//        log.info("demo select :{}",demoMapper.selectDemo(demo.getId()));
    }
}
