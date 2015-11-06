package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
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

    private final User user = new User(null, "login3", "password3", "firstname3", "secondname3");

//    private final String LOGIN = "login3";


    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userDao.getAllUsers();
        assertTrue(users.size() == 2);

    }

    @Test
    public void testAddUser() throws Exception {
        Integer id_user = userDao.addUser(user);
        assertNotNull(id_user);
        User newUser = userDao.getUserById(id_user);
        assertEquals(user.getLogin(), newUser.getLogin());
        assertEquals(user.getPassword(), newUser.getPassword());
        assertEquals(user.getFirstname(), newUser.getFirstname());
        assertEquals(user.getSecondname(), newUser.getSecondname());
    }

    @Test
    public void testDeleteUser() throws Exception {
        List<User> users = userDao.getAllUsers();
        Integer usersSize = users.size();
        assertTrue(users.size() > 0);
        userDao.deleteUser(users.get(0).getId_user());
        assertTrue(usersSize -1 == userDao.getAllUsers().size());

    }

}