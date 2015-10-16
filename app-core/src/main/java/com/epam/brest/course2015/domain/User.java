package com.epam.brest.course2015.domain;


import java.util.Date;

public class User{
    private Integer userId;

    private String login;

    private String password;

    private Date createdDate;

    private Date updatedDate;

    public User(Integer userId, String login, String password, Date createdDate, Date updatedDate) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public User() {

    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static enum UserFields {

        USER_ID ("userId"),
        LOGIN ("login"),
        PASSWORD ("password"),
        CREATED_DATE ("createdDate"),
        UPDATED_DATE ("updatedDate");

        private UserFields(String value){
            this.value = value;
        }

        private final String value;

        public String getValue(){return value;}

    }
}