package com.epam.brest.course2015.domain;

import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

/**
 * Created by user on 06.11.15.
 */
public class UserTest {


    private User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void testGetId_check() throws Exception {
        user.setId_check(1);
        assertEquals(user.getId_check(),(Integer)1);
    }

    @Test
    public void testGetSecondname() throws Exception {
        user.setSecondname("secondname");
        assertEquals(user.getSecondname(), "secondname");
    }

    @Test
    public void testGetFirstname() throws Exception {
        user.setFirstname("firstname");
        assertEquals(user.getFirstname(), "firstname");
    }

    @Test
    public void testGetPassword() throws Exception {
        user.setPassword("password");
        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("login");
        assertEquals(user.getLogin(), "login");
    }

    @Test
    public void testGetId_user() throws Exception {
        user.setId_user(1);
        assertEquals(user.getId_user(),(Integer)1);
    }
}