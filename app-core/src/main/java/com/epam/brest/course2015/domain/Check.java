package com.epam.brest.course2015.domain;

/**
 * Created by user on 06.11.15.
 */

public class Check {

    private Integer id_check;

    private Integer checknumber;

    private Integer summa;

    private Integer id_transaction;

    public Check(){
    }

    public Check(Integer id_check, Integer checknumber, Integer summa, Integer id_transaction){
        this.id_check = id_check;
        this.checknumber = checknumber;
        this.summa = summa;
        this.id_transaction = id_transaction;
    }

    public Integer getId_check() {
        return id_check;
    }

    public void setId_check(Integer id_check) {
        this.id_check = id_check;
    }

    public Integer getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(Integer checknumber) {
        this.checknumber = checknumber;
    }

    public Integer getSumma() {
        return summa;
    }

    public void setSumma(Integer summa) {
        this.summa = summa;
    }

    public Integer getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(Integer id_transaction) {
        this.id_transaction = id_transaction;
    }

    public static enum CheckField{
        ID_CHEK("id_check"),
        CHECKNUMBER("checknumber"),
        SUMMA("summa"),
        ID_TRANSACTION("id_transaction");

        private final String value;

         CheckField(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }

    }
}
