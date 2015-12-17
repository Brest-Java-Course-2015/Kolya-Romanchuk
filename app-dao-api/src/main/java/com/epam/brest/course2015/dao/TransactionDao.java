package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 06.11.15.
 */

public interface TransactionDao {

    public Integer totalFilterSumm(Integer id_user, Date date_from, Date date_before);
    public Integer totalSumm(Integer id_user);
    public List<Transaction> getAllTransactions(Integer id_user);
    public Integer addTransaction(Transaction transaction);
    public Transaction getTransactionById(Integer id_transaction);
    public List<Transaction> getFiltertransactions(Integer id_user, Date date_from, Date date_before);

}
