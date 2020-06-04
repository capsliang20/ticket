package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.ShardDemo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardDemoMapper {
    List<ShardDemo> queryDemo(@Param("id") Long id, @Param("areaCode") Integer areaCode);

    Integer addDemo(ShardDemo shardDemo);

    Integer removeDemo(@Param("id") Long id, @Param("areaCode") Integer areaCode);
}
