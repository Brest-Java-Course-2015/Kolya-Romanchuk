package com.epam.brest.course2015.domain;

import java.util.Date;

/**
 * Created by user on 06.11.15.
 */

public class Transaction {

    private Integer id_transaction;

    private Integer checknumbersender;

    private Integer checknumberrecipient;

    private Date date;

    private Integer summa;

    public Transaction(){
    }

    public Transaction(Integer id_transaction, Integer checknumbersender,Integer checknumberrecipient,
                       Integer summa, Date date){
        this.id_transaction = id_transaction;
        this.checknumbersender = checknumbersender;
        this.checknumberrecipient = checknumberrecipient;
        this.summa = summa;
        this.date = date;
    }

    public Integer getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(Integer id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Integer getChecknumbersender() {
        return checknumbersender;
    }

    public void setChecknumbersender(Integer checknumbersender) {
        this.checknumbersender = checknumbersender;
    }

    public Integer getChecknumberrecipient() {
        return checknumberrecipient;
    }

    public void setChecknumberrecipient(Integer checknumberrecipient) {
        this.checknumberrecipient = checknumberrecipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSumma() {
        return summa;
    }

    public void setSumma(Integer summa) {
        this.summa = summa;
    }

    public static enum TransactionFields{

        ID_TRANSACTION("id_transaction"),
        CHECKNUMBERSENDER ("checknumbersender"),
        CHECKNUMBERRECIPIENT("checknumberrecipient"),
        SUMMA("summa"),
        DATE("date");

        TransactionFields(String value){
            this.value = value;
        }

        private final String value;

        public String getValue(){
            return this.value;
        }

    }
}