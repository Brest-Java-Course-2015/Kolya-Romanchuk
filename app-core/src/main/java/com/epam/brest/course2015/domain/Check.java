package com.epam.brest.course2015.domain;

/**
 * Created by user on 06.11.15.
 */

public class Check {

    private Integer id_check;

    private Integer checknumber;

    private Integer summa;

    private Integer id_user;

    public Check(){
    }


    public Check(Integer id_check, Integer checknumber, Integer summa, Integer id_user){
        this.id_check = id_check;
        this.checknumber = checknumber;
        this.summa = summa;
        this.id_user = id_user;
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

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public static enum CheckField{
        ID_CHEK("id_check"),
        CHECKNUMBER("checknumber"),
        SUMMA("summa"),
        ID_USER("id_user");

        private final String value;

         CheckField(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }

    }
}
