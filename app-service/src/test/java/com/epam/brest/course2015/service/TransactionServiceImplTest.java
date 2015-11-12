package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 09.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class TransactionServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Transaction transaction = new Transaction(null, 123456, 546546, 20000, null, 3);

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testGetAllTransactions() throws Exception {
        LOGGER.debug("test: getAllTransaction()");
        assertTrue(transactionService.getAllTransactions().size() > 0);
    }

    @Test
    public void testAddTransaction() throws Exception {
        LOGGER.debug("test: addTransaction()");
        Integer countTransactions = transactionService.getAllTransactions().size();
        transactionService.addTransaction(transaction);
        assertTrue(countTransactions + 1 == transactionService.getAllTransactions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTransaction() throws Exception{
        LOGGER.debug("test: addNullTransaction()");
        transactionService.addTransaction(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionWithNotNullId() throws Exception{
        LOGGER.debug("test: addTransactionNotNullId()");
        Transaction transaction = new Transaction();
        transaction.setId_transaction(1);
        transactionService.addTransaction(transaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionWithNullCheckNumberSender() throws Exception{
        LOGGER.debug("test: addTransactionNullCheckNumberSender()");
        Transaction transaction = new Transaction();
        transaction.setChecknumberrecipient(456789);
        transaction.setSumma(21564);
        transactionService.addTransaction(transaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionWithNullCheckNumberRecipient() throws Exception{
        LOGGER.debug("test: addTransactionNullCheckNumberRecipient()");
        Transaction transaction = new Transaction();
        transaction.setChecknumbersender(456789);
        transaction.setSumma(21564);
        transactionService.addTransaction(transaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransactionWithNullSumma() throws Exception{
        LOGGER.debug("test: addTransactionNullSumma()");
        Transaction transaction = new Transaction();
        transaction.setChecknumberrecipient(456789);
        transaction.setChecknumbersender(21564);
        transactionService.addTransaction(transaction);
    }

}