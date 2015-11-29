package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 10.11.15.
 */
@RestController
public class UserRestController{

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//    public @ResponseBody

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<User> getAllUsers(){
        LOGGER.debug("getAllUsers()");
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addUser(@RequestBody User user){
        LOGGER.debug("addUser(): user_id = {} ",user.getId_user());
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody User getUserByLogin(@PathVariable(value = "login") String login){
        LOGGER.debug("getUserByLogin(): login = {} ",login);
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody User getUserById(@PathVariable(value = "id") Integer Id){
        LOGGER.debug("getUserById(): id = {} ",Id);
        return userService.getUserById(Id);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteUser(@RequestBody Integer Id){
        LOGGER.debug("deleteUser(): id = {} ",Id);
        userService.deleteUser(Id);
    }
}
