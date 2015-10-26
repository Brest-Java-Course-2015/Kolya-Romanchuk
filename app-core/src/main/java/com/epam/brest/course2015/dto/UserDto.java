package com.epam.brest.course2015.dto;

import com.epam.brest.course2015.domain.User;

import java.util.List;

public class UserDto {

    private List<User> users;

    private Integer total;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }



}
