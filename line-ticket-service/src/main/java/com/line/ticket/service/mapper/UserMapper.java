package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    Integer verifyLogin(@Param("account") String account,@Param("password") String password);
    User getUserLoginInfo(@Param("id")Integer id);
}
