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

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Integer id_user =1;

    private static Transaction tran= new Transaction(null,1234,1234,1, null, 1);

    @Test
    public void testGetAllTransactions() throws Exception {
        LOGGER.debug("test: getAllTransactions");
        List<Transaction> transactions = transactionDao.getAllTransactions(id_user);
        LOGGER.debug(">> size = {} ",transactions.size());
        assertTrue(transactions.size() == 2);
    }

    @Test
    public void testTotalSumm(){
        LOGGER.debug("test: totalSumm");
        Integer summa = transactionDao.totalSumm(id_user);
        assertTrue(summa == 6542);
    }

    @Test
    public void testTotalFilterSumm() throws Exception{
        LOGGER.debug("test: TotalFilterSumm");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datefrom = "2015-10-21";
        String datebefore = "2015-10-21";
        Date dateFrom = format.parse(datefrom);
        Date dateBefore = format.parse(datebefore);
        LOGGER.debug(">> dateFrom = {} ", dateFrom);
        LOGGER.debug(">> dateBefore = {} ", dateBefore);
        Integer summa = transactionDao.totalFilterSumm(id_user,dateFrom,dateBefore);
        assertTrue(summa == 2000);
    }

    @Test
    public void testAddTransaction() throws Exception {
        LOGGER.debug("test: addTransaction");
        Integer id_transaction = transactionDao.addTransaction(tran);
        assertNotNull(id_transaction);
        Transaction newTransaction = transactionDao.getTransactionById(id_transaction);
        assertEquals(newTransaction.getId_transaction(),id_transaction);
        List<Transaction> transactions = transactionDao.getAllTransactions(id_user);
        assertTrue(transactions.size() == 3);

    }

    @Test
    public void testGetFiltertransactions() throws Exception{
        LOGGER.debug("test: getFilterTransactions");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datefrom = "2015-10-21";
        String datebefore = "2015-10-21";
        Date dateFrom = format.parse(datefrom);
        Date dateBefore = format.parse(datebefore);
        LOGGER.debug(">> dateFrom = {} ", dateFrom);
        LOGGER.debug(">> dateBefore = {} ", dateBefore);
        List<Transaction> transactions = transactionDao.getFiltertransactions(id_user,dateFrom,dateBefore);
        assertTrue(transactions.size() == 1);
    }

}