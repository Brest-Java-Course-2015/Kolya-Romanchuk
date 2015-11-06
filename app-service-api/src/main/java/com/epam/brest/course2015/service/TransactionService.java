package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Transaction;

import java.util.List;

/**
 * Created by user on 06.11.15.
 */
public interface TransactionService {
    public List<Transaction> getAllTransactions();
    public Integer addTransaction(Transaction transaction);
    public void deleteTransaction(Integer id_transaction);
    public Transaction getTransactionById(Integer id_transaction);
    public void logTransaction(Transaction transaction);

}

