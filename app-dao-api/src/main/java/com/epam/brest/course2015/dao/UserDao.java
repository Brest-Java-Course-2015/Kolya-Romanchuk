package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;

import java.util.List;


public interface UserDao {

    public User getUserByLogin (String login);
    public User getUserById (Integer id);
    public List<User> getAllUsers();
    public void deleteUser (Integer id);
    public Integer addUser (User user);
    public void updateUser (User user);
}