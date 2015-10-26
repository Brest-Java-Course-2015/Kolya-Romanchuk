package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public Integer addUser(User user);

    public void updateUser (User user);

    public User getUserById(Integer userId);

    public User getUserByLogin(String login);

    public void deleteUser(Integer userId);

    public UserDto getUserDto();
    
    public void logUser(User user);
}
