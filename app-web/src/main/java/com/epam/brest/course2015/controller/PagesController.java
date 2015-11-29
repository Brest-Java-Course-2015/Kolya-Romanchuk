package com.epam.brest.course2015.controller;

import com.epam.brest.course2015.service.*;
import com.epam.brest.course2015.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by user on 20.11.15.
 */
@Controller
public class PagesController {

    @Autowired
    private UserService userService;
//
//    @Autowired
//    private CheckService checkService;
//
//    @Autowired
//    private TransactionService transactionService;

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout){
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = {"/user","/"},method = RequestMethod.GET)
    public ModelAndView userPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(){
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("admin","users",users);
        return modelAndView;
    }
}
