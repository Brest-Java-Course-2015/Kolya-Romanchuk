package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Transaction;
import com.epam.brest.course2015.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 10.11.15.
 */
@RestController
public class TransactionRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions/{id_user}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Transaction> getAllTransaction(@PathVariable(value = "id_user") Integer id_user){
        LOGGER.debug("getAllTransactions()");
        return transactionService.getAllTransactions(id_user);
    }

    @RequestMapping(value = "/transaction/summ/{id_user}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer totalSumm(@PathVariable(value = "id_user") Integer id_user){
        LOGGER.debug("totalSumm(): id_user = {} ",id_user);
        return transactionService.totalSumm(id_user);
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

    @RequestMapping(value = "/transactions/{id_user}/filter/date/{datefrom}/{datebefore}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Transaction> getFilterTransaction(@PathVariable (value = "id_user") Integer id_user,
                                                                @PathVariable(value = "datefrom") String date_from,
                                                                @PathVariable(value = "datebefore") String date_before)
            throws ParseException {

        LOGGER.debug("getFilterTransaction()");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= format.parse(date_from);
        Date dateBefore= format.parse(date_before);
        return transactionService.getFiltertransactions(id_user,dateFrom,dateBefore);
    }

    @RequestMapping(value = "/transactions/summ/{id_user}/filter/date/{datefrom}/{datebefore}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer totalFilterSumm(@PathVariable (value = "id_user") Integer id_user,
                                                 @PathVariable(value = "datefrom") String date_from,
                                                 @PathVariable(value = "datebefore") String date_before) throws ParseException{
        LOGGER.debug("totalFilterSumm(): id_user = {} ",id_user);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= format.parse(date_from);
        Date dateBefore= format.parse(date_before);
        return transactionService.totalFilterSumm(id_user, dateFrom,dateBefore);
    }
}
