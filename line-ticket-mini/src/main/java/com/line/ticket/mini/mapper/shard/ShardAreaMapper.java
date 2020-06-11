package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardAreaMapper {
    List<Area> queryAreaList();

    Integer addArea(Area sharedArea);

    Integer removeArea(@Param("areaCode") Integer areaCode);
}
