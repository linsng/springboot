package com.lsg.service;

import com.lsg.Application;
import com.lsg.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author lsg
 * @version 1.0
 * @date 2018/7/20
 * @since 1.0
 */
//@RunWith(SpringJUnit4ClassRunner.class) //SpringJunit支持
@SpringApplicationConfiguration(classes = Application.class) // 指定SpringBoot工程的Application启动类
//@WebAppConfiguration
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testHasMatchUser() throws Exception {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

}