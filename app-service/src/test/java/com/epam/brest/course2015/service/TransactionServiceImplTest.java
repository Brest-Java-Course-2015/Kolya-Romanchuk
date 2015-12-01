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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by user on 09.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class TransactionServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Transaction transaction = new Transaction(null, 1234, 121234, 20000, null, 1);

    private final Integer id_user = 1;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testGetAllTransactions() throws Exception {
        LOGGER.debug("test: getAllTransaction()");
        assertTrue(transactionService.getAllTransactions(id_user).size() > 0);
    }
//
    @Test
    public void testAddTransaction() throws Exception {
        LOGGER.debug("test: addTransaction()");
        Integer countTransactions = transactionService.getAllTransactions(id_user).size();
        transactionService.addTransaction(transaction);
        assertTrue(countTransactions + 1 == transactionService.getAllTransactions(id_user).size());
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetFilterTransaction() throws Exception{
        LOGGER.debug("test: getFilterTransaction()");
        String date_before = "2015-10-20";
        String date_from = "2015-11-30";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= format.parse(date_from);
        Date dateBefore= format.parse(date_before);
        transactionService.getFiltertransactions(id_user,dateFrom,dateBefore);
    }

}