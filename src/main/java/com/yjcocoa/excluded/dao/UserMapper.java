package com.yjcocoa.excluded.dao;

import com.yjcocoa.excluded.po.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserMapper.java
 * <p>
 * Created by 阳君 on 2017/10/26.
 * Copyright © 2017年 mybatis. All rights reserved.
 */
public interface UserMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    void insertUser(User user);

    void deleteUser(String code);

    void updateUser(User user);

    List<User> selectUsers();

}
