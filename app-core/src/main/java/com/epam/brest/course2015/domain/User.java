package com.epam.brest.course2015.domain;

/**
 * Created by user on 06.11.15.
 */

public class User {

    private Integer id_user;

    private String login;

    private String password;

    private String firstname;

    private String lastname;

    public User(){
    }

    public User(Integer id_user, String login, String password, String firstname, String lastname){
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
        LASTNAME ("lastname");

        UserFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue(){
            return this.value;
        }
    }

}