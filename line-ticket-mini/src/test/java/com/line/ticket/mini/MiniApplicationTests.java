package com.line.ticket.mini;

import com.line.ticket.mini.mapper.shard.ShardAreaMapper;
import com.line.ticket.mini.mapper.shard.ShardDemoMapper;
import com.line.ticket.mini.mapper.shard.ShardUserMapper;
import com.line.ticket.mini.mapper.shard.ShardUserRelationMapper;
import com.line.ticket.mini.mapper.single.SingleDemoMapper;
import com.line.ticket.mini.model.shard.ShardUser;
import com.line.ticket.mini.model.shard.ShardUserRelation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
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

    @Test
    void contextLoads() {
        ShardUser shardUser = new ShardUser();
        shardUser.setUserKey("user_user_key");
        shardUser.setUserValue("user_user_value");
        shardUser.setAreaCode(11);
        shardUserMapper.addUser(shardUser);
        ShardUserRelation shardUserRelation = new ShardUserRelation();
        shardUserRelation.setUserId(shardUser.getId());
        shardUserRelation.setUserAccount("qwwaq245553");
        shardUserRelation.setUserPassword("password_123");
        shardUserRelation.setAreaCode(shardUser.getAreaCode());
        shardUserRelationMapper.addUserRelation(shardUserRelation);
        System.out.println(shardUserMapper.queryAllUser());
//        ShardDemo shardDemo=new ShardDemo();
//        shardDemo.setName("demoName");
//        shardDemo.setAreaCode(10);
//        shardDemo.setDateNum(20200603);
//        shardDemoMapper.addDemo(shardDemo);
//        System.out.println(shardDemo);
//        System.out.println("db: "+shardDemo.getId()%2);
//        System.out.println("tb: "+shardDemo.getId()%8);

//        ShardArea shardArea = new ShardArea();
//        shardArea.setCode(11);
//        shardArea.setName("广州");
//        shardAreaMapper.addArea(shardArea);
//        System.out.println(shardArea);

//        ShardUserRelation shardUserRelation = new ShardUserRelation();
//        shardUserRelation.setAreaCode(10);
//        shardUserRelation.setUserId(475387166889869312l);
//        shardUserRelation.setUserAccount("qwwaq@qq.com");
//        shardUserRelation.setUserPassword("password_qwwaq");
//        shardUserRelationMapper.addUserRelation(shardUserRelation);
//        System.out.println(shardUserRelation);
    }

}
