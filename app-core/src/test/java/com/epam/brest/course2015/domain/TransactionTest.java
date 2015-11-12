package com.epam.brest.course2015.domain;

import com.epam.brest.course2015.domain.Transaction;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by user on 06.11.15.
 */
public class TransactionTest {

    private Transaction transaction;

    @Before
    public void setUp(){
        transaction = new Transaction();
    }

    @Test
    public void testGetId_transaction() throws Exception {
        transaction.setId_transaction(1);
        assertEquals(transaction.getId_transaction(), (Integer) 1);
    }

    @Test
    public void testGetChecknumbersender() throws Exception {
        transaction.setChecknumbersender(113244687);
        assertEquals(transaction.getChecknumbersender(),(Integer)113244687);
    }

    @Test
    public void testGetChecknumberrecipient() throws Exception {
        transaction.setChecknumberrecipient(112345464);
        assertEquals(transaction.getChecknumberrecipient(),(Integer)112345464);
    }

    @Test
    public void testGetDate() throws Exception {
        transaction.setDate(new Date(5,11,2015));
        assertEquals(transaction.getDate(),new Date(5,11,2015));
    }

    @Test
    public void testGetSumma() throws Exception {
        transaction.setSumma(1);
        assertEquals(transaction.getSumma(),(Integer)1);
    }

    @Test
    public void testGetIdCheck() throws Exception{
        transaction.setId_check(1);
        assertEquals(transaction.getId_check(),(Integer)1);
    }
}