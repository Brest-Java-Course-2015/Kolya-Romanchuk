package com.epam.brest.course2015.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User();

    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("LOGIN");
        assertEquals("LOGIN", user.getLogin());
    }


    @Test
    public void testGetPassword() throws Exception {
        user.setPassword("PASSWORD");
        assertEquals("PASSWORD", user.getPassword());

    }

    @Test
    public void testGetCreatedDate() throws Exception {
        user.setCreatedDate(new Date(2015,10,7));
        assertEquals(new Date(2015,10,7),user.getCreatedDate());

    }

    @Test
    public void testGetUserId() throws Exception {
        user.setUserId(1);
        assertEquals((Integer) 1, user.getUserId());
    }
}