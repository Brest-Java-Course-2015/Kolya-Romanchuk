package com.epam.brest.course2015.domain;

import com.epam.brest.course2015.domain.Check;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 06.11.15.
 */
public class CheckTest {

    private Check check;

    @Before
    public void setUp(){
        check = new Check();
    }

    @Test
    public void testGetId_check() throws Exception {
        check.setId_check(1);
        assertEquals(check.getId_check(), (Integer) 1);
    }

    @Test
    public void testGetChecknumber() throws Exception {
        check.setChecknumber(1);
        assertEquals(check.getChecknumber(), (Integer)1);
    }

    @Test
    public void testGetSumma() throws Exception {
        check.setSumma(1);
        assertEquals(check.getSumma(), (Integer)1);

    }

    @Test
    public void testGetId_transaction() throws Exception {
        check.setId_user(1);
        assertEquals(check.getId_user(), (Integer)1);
    }
}