package com.test;

import com.yjcocoa.excluded.po.User;
import com.yjcocoa.excluded.service.UserService;
import com.yjcocoa.mybatis.DynamicDataSourceHolder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MyBatisTest.java
 * <p>
 * Created by 阳君 on 2017/10/29.
 * Copyright © 2017年 yjcocoa. All rights reserved.
 */
public class MyBatisTest {

    @Test
    public void test() throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
        UserService userService = appContext.getBean(UserService.class);
        System.out.println("默认从库");
        System.out.println(userService.selectUsers()); //从库查询
        // 主增 从查
        User user = new User("937447974-1", "阳君");
        DynamicDataSourceHolder.setMaster(true, () -> { // 主库增加
            System.out.println("切换到主库");
            userService.insertUser(user);
            System.out.println(userService.selectUsers());
        });
        try {
            DynamicDataSourceHolder.setMaster(false, () -> {
                System.out.println("切换到从库");
                System.out.println(userService.selectUsers()); // xml开启缓存会查询到主库数据
                userService.insertUser(user); // 从库插入失败
            });
        } catch (Exception e) {
            System.out.println("从库操作报错");
            user.setCode("93447974-2");
            DynamicDataSourceHolder.setMaster(true, () -> {// 回到主库增加成功
                System.out.println("切换到主库");
                userService.insertUser(user);
                System.out.println(userService.selectUsers());
            });
        }
    }

}
