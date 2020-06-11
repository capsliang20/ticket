package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.Demo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardDemoMapper {
    List<Demo> queryDemo(@Param("id") Long id, @Param("areaCode") Integer areaCode);

    Integer addDemo(Demo demo);

    Integer removeDemo(@Param("id") Long id, @Param("areaCode") Integer areaCode);
}
