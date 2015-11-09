package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;


import static org.easymock.EasyMock.*;

/**
 * Created by user on 09.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service-mock.xml"})
public class UserServiceImplMockTest {

    private User user = new User(null,"login3","password3",null,null);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao mockUserDao;

    @After
    public void clean() {
        verify(mockUserDao);
        reset(mockUserDao);
    }

    @Test
    public void testLogUser(){
        replay(mockUserDao);
        userService.logUser(user);
    }

    @Test
    public void testGetUserByLogin(){
        expect(mockUserDao.getUserByLogin(user.getLogin())).andReturn(user);
        replay(mockUserDao);
        User result = userService.getUserByLogin(user.getLogin());
        Assert.assertTrue(result == user);
    }

    @Test
    public void testAddUser(){
        expect(mockUserDao.addUser(user)).andReturn(4);
        replay(mockUserDao);
        Integer id = userService.addUser(user);
        Assert.assertTrue(id == 4);
    }

}

