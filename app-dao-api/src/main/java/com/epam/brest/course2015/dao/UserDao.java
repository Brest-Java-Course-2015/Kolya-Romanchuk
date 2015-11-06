package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;

import java.util.List;

/**
 * Created by user on 06.11.15.
 */
public interface UserDao {

    public List<User> getAllUsers();
    public Integer addUser(User user);
    public void deleteUser(Integer id_user);
//    public void updateUser(User user);
    public User getUserById(Integer id_user);
    public User getUserByLogin(String login);

}
