package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.service.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 验证登录
     * @param account 用户账号
     * @param password 用户密码(尚未加密)
     * @return 用户id
     */
    Integer verifyLogin(@Param("account") String account, @Param("password") String password);

    /**
     * 获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User getUserLoginInfo(@Param("id") Integer id);
}
