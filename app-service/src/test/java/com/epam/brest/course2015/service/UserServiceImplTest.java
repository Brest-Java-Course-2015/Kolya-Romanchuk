package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 09.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final User user = new User(null, "login3", "password3", "firstname3", "secondname3");

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("test: GetAllUsers()");
        assertTrue(userService.getAllUsers().size() > 0);
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("test: deleteUser()");
        List<User> users = userService.getAllUsers();
        userService.deleteUser(users.get(0).getId_user());
        assertTrue(users.size() - 1 == userService.getAllUsers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception{
        LOGGER.debug("test: addNullUser()");
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotNullId() throws Exception{
        LOGGER.debug("test: addUserWithNotNullId()");
        User user = new User();
        user.setId_user(1);
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullLogin() throws Exception{
        LOGGER.debug("test: addUserWithNullLogin()");
        User user = new User();
        user.setPassword("passowrd");
        userService.addUser(user);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullPassword() throws Exception{
        LOGGER.debug("test: addUserWithNullPassword()");
        User user = new User();
        user.setLogin("login");
        userService.addUser(user);
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUsers()");
        Integer SizeUsers = userService.getAllUsers().size();
        userService.addUser(user);
        assertTrue(SizeUsers + 1 == userService.getAllUsers().size());
    }
}