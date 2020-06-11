package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.FullUser;
import com.line.ticket.mini.model.shard.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardUserMapper {
    List<User> queryUser(@Param("id") Long id, @Param("areaCode") Integer areaCode);

    List<FullUser> queryAllUser();

    Integer addUser(User user);

    Integer removeUser(@Param("id") Long id, @Param("areaCode") Integer areaCode);
}
