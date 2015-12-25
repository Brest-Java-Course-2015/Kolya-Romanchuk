package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.junit.Assert.*;

/**
 * Created by user on 06.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class UserDaoImplTest {

    private final Logger LOGGER = LogManager.getLogger();

    private final User user = new User(null, "login3", "password3", "firstname3", "secondname3");

    private final String LOGIN = "login1";

    private Integer ID_USER = 1;

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("test: getAllUsers");
        List<User> users = userDao.getAllUsers();
        LOGGER.debug(">> Lastname 1 = {} ", users.get(0).getLastname());
        LOGGER.debug(">> Lastname 2 = {} ", users.get(1).getLastname());
        assertTrue(users.size() == 2);

    }

    @Test
    public void testCountUser() throws Exception {
        LOGGER.debug("test: countUser()");
        Integer count = userDao.countUser();
        assertTrue(count == 2);
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUser");
        Integer id_user = userDao.addUser(user);
        assertNotNull(id_user);
        User newUser = userDao.getUserById(id_user);
        assertEquals(user.getLogin(), newUser.getLogin());
        assertEquals(user.getPassword(), newUser.getPassword());
        assertEquals(user.getFirstname(), newUser.getFirstname());
        assertEquals(user.getLastname(), newUser.getLastname());
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("test: DeleteUser");
        List<User> users = userDao.getAllUsers();
        Integer usersSize = users.size();
        assertTrue(users.size() > 0);
        userDao.deleteUser(users.get(0).getId_user());
        assertTrue(usersSize -1 == userDao.getAllUsers().size());

    }

}