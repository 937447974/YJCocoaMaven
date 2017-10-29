package com.yjcocoa.excluded.service.impl;

import com.yjcocoa.excluded.dao.UserMapper;
import com.yjcocoa.excluded.po.User;
import com.yjcocoa.excluded.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * UserServiceImpl.java
 * <p>
 * Created by 阳君 on 2017/10/26.
 * Copyright © 2017年 mybatis. All rights reserved.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertUser(User user) {
        this.userMapper.insertUser(user);
    }

    @Transactional
    public void deleteUser(String code) {
        if (!StringUtils.isEmpty(code)) {
            this.userMapper.deleteUser(code);
        }
    }

    public void updateUser(User user) {
        if (!StringUtils.isEmpty(user.getCode())) {
            this.userMapper.updateUser(user);
        }
    }

    public List<User> selectUsers() {
        List<User> list = this.userMapper.selectUsers();
        return list;
    }

}
