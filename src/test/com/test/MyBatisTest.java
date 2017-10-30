package com.test;

import com.yjcocoa.excluded.po.User;
import com.yjcocoa.excluded.service.UserService;
import com.yjcocoa.mybatis.DynamicDataSourceHolder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * MyBatisTest.java
 * <p>
 * Created by 阳君 on 2017/10/29.
 * Copyright © 2017年 yjcocoa. All rights reserved.
 */
public class MyBatisTest {
    private UserService userService;

    @Test
    public void test() {
        this.buildData();
        // 主增 从查
        User user = new User("937447974-one", "阳君");
        try {
            DynamicDataSourceHolder.setMaster(true, () -> {
                this.userService.insertUser(user);
                System.out.println(this.userService.selectUsers());
            });
            DynamicDataSourceHolder.setMaster(false, () -> {
                System.out.println(this.userService.selectUsers());
                this.userService.insertUser(user);
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }

//    @Test
//    public void masterTest1() {
//        // 主增主查
//        this.buildData();
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        DynamicDataSourceHolder.setMaster(true);
//        User user = new User("937447974-one2", "阳君");
//        this.userService.insertUser(user);
//        System.out.println(this.userService.selectUsers());
//        DynamicDataSourceHolder.setMaster(true);// 测试切换是否会回滚
//        transactionManager.commit(status);
//        System.out.println(this.userService.selectUsers());
//    }

    private void userTest() {
        User user = new User("937447974-one", "阳君");
        this.userService.insertUser(user);

        user.setName("yangjun");
        this.userService.updateUser(user);

        System.out.println(this.userService.selectUsers());

        this.userService.deleteUser(user.getCode());
    }

    private void buildData() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
        this.userService = appContext.getBean(UserService.class);
    }

}
