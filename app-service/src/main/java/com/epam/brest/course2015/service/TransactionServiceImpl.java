package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.CheckDao;
import com.epam.brest.course2015.dao.TransactionDao;
import com.epam.brest.course2015.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 09.11.15.
 */
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LogManager.getLogger();

    private TransactionDao transactionDao;

    public void setTransactionDao (TransactionDao transactionDao){
        this.transactionDao = transactionDao;
    }

    public List<Transaction> getAllTransactions(Integer id_user) {
        LOGGER.debug("getAllTransaction()");
        return transactionDao.getAllTransactions(id_user);
    }

    public Integer addTransaction(Transaction transaction) {
        Assert.notNull(transaction, "Transaction should not be null");
        LOGGER.debug("addTransaction(): numberchecksender = {} ", transaction.getChecknumbersender());
         Assert.isNull(transaction.getId_transaction(), "Id Transaction should be null");
        Assert.notNull(transaction.getChecknumbersender(), "CheckNumberSender should not be null");
        Assert.notNull(transaction.getChecknumberrecipient(), "CheckNumberRecipient should not be null");
        Assert.notNull(transaction.getSumma(), "Summa Transaction should not be null");
        return transactionDao.addTransaction(transaction);
    }

    public void deleteTransaction(Integer id_transaction) {
        LOGGER.debug("deleteTransaction(): id_transaction = {} ", id_transaction);
        Assert.notNull(id_transaction, "Id Transaction should not be null");
        Assert.isTrue(id_transaction > 0);
        transactionDao.deleteTransaction(id_transaction);
    }

    public Transaction getTransactionById(Integer id_transaction) {
        LOGGER.debug("gettransactionById: id_transaction = {} ",id_transaction);
        Assert.notNull(id_transaction, "Id Transaction should not be null");
        Assert.isTrue(id_transaction > 0);
        return transactionDao.getTransactionById(id_transaction);
    }

    public void logTransaction(Transaction transaction) {
        LOGGER.debug("logTransaction:  id_transaction = {} ", transaction.getId_transaction());
    }

    public List<Transaction> getFiltertransactions(Integer id_user, Date date_from, Date date_before) {
        LOGGER.debug("getFiltertransaction");
        Assert.isTrue(date_from.getTime() <= date_before.getTime(),"From Date < = Before Date");
        return transactionDao.getFiltertransactions(id_user ,date_from, date_before);
    }
}
