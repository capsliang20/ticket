package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.ShardUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardUserRelationMapper {

    List<ShardUserRelation> queryUserRelation(@Param("userId") Long userId, @Param("areaCode") Integer areaCode);

    Integer addUserRelation(ShardUserRelation shardUserRelation);

    Integer removeUserRelations(@Param("userId") Long userId, @Param("areaCode") Integer areaCode);

}
