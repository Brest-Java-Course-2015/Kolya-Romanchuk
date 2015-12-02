package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by user on 09.11.15.
 */

@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();
    }

    public void deleteUser(Integer id_user) {
        LOGGER.debug("deleteUser(): id_user = {} ", id_user);
        Assert.notNull(id_user,"Id user should not be null.");
        Assert.isTrue(id_user > 0);
        userDao.deleteUser(id_user);
    }

    public User getUserById(Integer id_user) {
        LOGGER.debug("getUserById(): id_user = {} ", id_user);
        Assert.notNull(id_user, "Id user should not be null");
        Assert.isTrue(id_user > 0);
        return userDao.getUserById(id_user);
    }

    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin(): login = {}", login);
        Assert.hasText(login, "User login should not be null.");
        return userDao.getUserByLogin(login);
    }

    public void logUser(User user) {
        LOGGER.debug("logUser() : id_user = {} ", user.getId_user());
    }

    public Integer countUser() {
        LOGGER.debug("countuser()");
        return userDao.countUser();
    }


    public Integer addUser(User user) {
        Assert.notNull(user, "User should not be null.");
        LOGGER.debug("addUser(): user login = {} ",user.getLogin());
        Assert.isNull(user.getId_user(), "Id User should be.");
        Assert.hasText(user.getLogin(), "Login user should not be null.");
        Assert.hasText(user.getPassword(), "Password user should not be null.");
        return userDao.addUser(user);
    }


}
