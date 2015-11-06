package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Transaction;
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
public class TransactionDaoImplTest {

    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TransactionDao transactionDao;

    private static Transaction tran= new Transaction(null,1234567,13456487,20054,null);

    @Test
    public void testGetAllTransactions() throws Exception {
        LOGGER.debug("test: getAllTransactions");
        List<Transaction> transactions = transactionDao.getAllTransactions();
        assertTrue(transactions.size() == 2);
    }

    @Test
    public void testAddTransaction() throws Exception {
        LOGGER.debug("test: addTransaction");
        Integer id_transaction = transactionDao.addTransaction(tran);
        assertNotNull(id_transaction);
        Transaction newTransaction = transactionDao.getTransactionById(id_transaction);
        assertEquals(newTransaction.getId_transaction(),id_transaction);
        List<Transaction> transactions = transactionDao.getAllTransactions();
        assertTrue(transactions.size() == 3);

    }

    @Test
    public void testDeleteTransaction() throws Exception {
        LOGGER.debug("test: deleteTransaction");
        List<Transaction> transactions = transactionDao.getAllTransactions();
        assertTrue(transactions.size() > 0);
        int sizeBefore = transactions.size();
        transactionDao.deleteTransaction(transactions.get(0).getId_transaction());
        assertTrue((sizeBefore - 1) == transactionDao.getAllTransactions().size());
    }

}