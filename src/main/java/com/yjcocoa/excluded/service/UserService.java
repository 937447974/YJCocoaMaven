package com.yjcocoa.excluded.service;

import com.yjcocoa.excluded.po.User;

import java.util.List;

/**
 * UserService.java
 * <p>
 * Created by 阳君 on 2017/10/26.
 * Copyright © 2017年 mybatis. All rights reserved.
 */
public interface UserService {

    void insertUser(User user);

    void deleteUser(String code);

    void updateUser(User user);

    List<User> selectUsers();

}
