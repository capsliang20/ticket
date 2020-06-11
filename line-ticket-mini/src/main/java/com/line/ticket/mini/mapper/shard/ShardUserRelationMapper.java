package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.UserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardUserRelationMapper {
    List<UserRelation> queryUserRelation(@Param("userId") Long userId, @Param("areaCode") Integer areaCode);

    Integer addUserRelation(UserRelation userRelation);

    Integer removeUserRelations(@Param("userId") Long userId, @Param("areaCode") Integer areaCode);

}
