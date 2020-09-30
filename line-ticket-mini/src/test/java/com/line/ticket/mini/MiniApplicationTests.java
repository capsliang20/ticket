package com.line.ticket.mini;

import com.line.ticket.mini.kafka.DefaultKafkaListener;
import com.line.ticket.mini.mapper.shard.*;
import com.line.ticket.mini.mapper.single.SingleDemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class MiniApplicationTests {

    @Resource
    ShardDemoMapper shardDemoMapper;

    @Resource
    SingleDemoMapper singleDemoMapper;

    @Resource
    ShardUserMapper shardUserMapper;

    @Resource
    ShardAreaMapper shardAreaMapper;

    @Resource
    ShardUserRelationMapper shardUserRelationMapper;

    @Resource
    ShardRecordMapper shardRecordMapper;

    @Autowired
    DefaultKafkaListener listener;

    @Autowired
    KafkaTemplate<String, String> template;

    @Test
    void contextLoads() throws Exception {
        template.send("annotated", "xin", "annotated1");
        template.send("annotated", "jin", "annotated2");
        template.send("annotated", "lni", "annotated3");
        template.send("annotated", "asd", "annotated4");
        template.send("annotated", "xin", "annotated5");
        template.flush();
        log.info("now waitConsumer");
        listener.getLatch().await();
    }
}
