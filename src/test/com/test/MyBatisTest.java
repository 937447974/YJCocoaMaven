package com.test;

import com.yjcocoa.excluded.po.User;
import com.yjcocoa.excluded.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * MyBatisTest.java
 * <p>
 * Created by 阳君 on 2017/10/29.
 * Copyright © 2017年 yjcocoa. All rights reserved.
 */
public class MyBatisTest {
    private ApplicationContext appContext;
    private UserService userService;

    @Test
    public void transactionTest() {
        this.userService = this.getAppContext().getBean(UserService.class);
        DataSourceTransactionManager txManager = this.getAppContext().getBean(DataSourceTransactionManager.class);
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(transactionDefinition); // 获得事务状态
        try {
            this.userTest();
            txManager.commit(status);
        } catch (Exception e) {
            System.out.println(e);
            txManager.rollback(status);
        }
    }

    private void userTest() {
        User user = new User("937447974-one", "阳君");
        this.userService.insertUser(user);

        user.setName("yangjun");
        this.userService.updateUser(user);

        System.out.println(this.userService.selectUsers());

        this.userService.deleteUser(user.getCode());
    }

    private ApplicationContext getAppContext() {
        if (appContext == null)
            appContext = new ClassPathXmlApplicationContext("spring-config.xml");
        return appContext;
    }

}
