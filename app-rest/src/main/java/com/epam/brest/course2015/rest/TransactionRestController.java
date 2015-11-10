package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Transaction;
import com.epam.brest.course2015.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 10.11.15.
 */
@RestController
public class TransactionRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Transaction> getAllTransaction(){
        LOGGER.debug("getAllTransactions()");
        return transactionService.getAllTransactions();
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addTransaction(@RequestBody Transaction transaction){
        LOGGER.debug("addTransaction(): transaction_id = {} ",transaction.getId_transaction());
        return transactionService.addTransaction(transaction);
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Transaction getTransactionById(@PathVariable(value = "id") Integer id){
        LOGGER.debug("getTransactionById(): id = {} ",id);
        return transactionService.getTransactionById(id);
    }
}
