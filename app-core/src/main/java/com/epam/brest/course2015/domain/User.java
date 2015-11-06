package com.epam.brest.course2015.domain;

/**
 * Created by user on 06.11.15.
 */

public class User {

    private Integer id_user;

    private String login;

    private String password;

    private String firstname;

    private String secondname;

    private Integer id_check;

    public User(){
    }

    public User(Integer id_user, String login, String password, String firstname, String secondname){
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
    }

    public Integer getId_check() {
        return id_check;
    }

    public void setId_check(Integer id_check) {
        this.id_check = id_check;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public static enum UserFields{
        ID_USER ("id_user"),
        LOGIN ("login"),
        PASSWORD ("password"),
        FIRSTNAME ("firstname"),
        SECONDNAME ("secondname"),
        ID_CHECK ("id_check");

        UserFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue(){
            return this.value;
        }
    }

}