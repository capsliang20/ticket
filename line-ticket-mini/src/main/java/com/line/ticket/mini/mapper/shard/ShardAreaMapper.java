package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.ShardArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardAreaMapper {

    List<ShardArea> queryAreaList();

    Integer addArea(ShardArea sharedArea);

    Integer removeArea(@Param("areaCode") Integer areaCode);
}
