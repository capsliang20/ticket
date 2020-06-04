package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.ShardFullUser;
import com.line.ticket.mini.model.shard.ShardUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardUserMapper {
    List<ShardUser> queryUser(@Param("id") Long id, @Param("areaCode") Integer areaCode);

    List<ShardFullUser> queryAllUser();

    Integer addUser(ShardUser shardUser);

    Integer removeUser(@Param("id") Long id, @Param("areaCode") Integer areaCode);
}
