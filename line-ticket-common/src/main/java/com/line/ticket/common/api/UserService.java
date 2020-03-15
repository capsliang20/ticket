package com.line.ticket.common.api;

import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.entity.service.User;

public interface UserService {
    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 用户信息
     */
    Result<User> login(String account, String password);
}
