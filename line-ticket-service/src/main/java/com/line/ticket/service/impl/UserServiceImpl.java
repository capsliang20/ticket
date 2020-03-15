package com.line.ticket.service.impl;

import com.line.ticket.common.api.UserService;
import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.entity.service.User;
import com.line.ticket.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> login(String account, String password) {
        Integer userId = userMapper.verifyLogin(account, password);
        return Result.success(userMapper.getUserLoginInfo(userId));
    }
}
