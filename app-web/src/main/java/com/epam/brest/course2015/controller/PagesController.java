package com.epam.brest.course2015.controller;

import com.epam.brest.course2015.service.*;
import com.epam.brest.course2015.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 20.11.15.
 */
@RestController
@Controller
public class PagesController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = {"/login","/"},method = RequestMethod.GET)
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

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView userPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/user/count",method = RequestMethod.GET)
    public @ResponseBody Integer countUser(){
        return userService.countUser();
    }

    @RequestMapping(value = "/user/{login}",method = RequestMethod.GET)
    public @ResponseBody List<Check> getCheck(@PathVariable(value = "login") String login){
        return checkService.getAllChecks(userService.getUserByLogin(login).getId_user());
    }

    @RequestMapping(value = {"/user/{login}/transaction"},method = RequestMethod.GET)
    public ModelAndView transactionPage(@PathVariable(value = "login") String login){
        List<Check> checks = checkService.getAllChecks(userService.getUserByLogin(login).getId_user());
        ModelAndView modelAndView = new ModelAndView("transaction","checks",checks);
        return modelAndView;
    }

    @RequestMapping(value = {"/user/{login}/transaction/check/{checknumber}"},method = RequestMethod.GET)
    public @ResponseBody Check getCheck(@PathVariable(value = "checknumber") Integer checknumber) {
        return checkService.getCheckByCheckNumder(checknumber);
    }

    @RequestMapping(value = {"/user/{login}/transaction/create"},method = RequestMethod.POST)
    public @ResponseBody Integer addTransaction(@RequestBody Transaction transaction){
        Integer id_check = checkService.getCheckByCheckNumder(transaction.getChecknumbersender()).getId_check();
        Integer summa = checkService.getCheckById(id_check).getSumma();
        if ( summa >= transaction.getSumma()){
            transaction.setId_check(id_check);
            return transactionService.addTransaction(transaction);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/user/{login}/extract/filter/{datefrom}/{datebefore}",method = RequestMethod.GET)
    public @ResponseBody List<Transaction> getFilterTransaction(@PathVariable (value = "login") String login,
                                                                @PathVariable(value = "datefrom") String date_from,
                                                                @PathVariable(value = "datebefore") String date_before) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= format.parse(date_from);
        Date dateBefore= format.parse(date_before);
        return transactionService.getFiltertransactions(userService.getUserByLogin(login).getId_user(), dateFrom, dateBefore);
    }

    @RequestMapping(value = "/user/{login}/extract/summ", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer totalSumm(@PathVariable(value = "login") String login){
        return transactionService.totalSumm(userService.getUserByLogin(login).getId_user());
    }

    @RequestMapping(value = "/user/{login}/extract/summ/{datefrom}/{datebefore}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer totalFilterSumm(@PathVariable (value = "login") String login,
                                                 @PathVariable(value = "datefrom") String date_from,
                                                 @PathVariable(value = "datebefore") String date_before) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= format.parse(date_from);
        Date dateBefore= format.parse(date_before);
        return transactionService.totalFilterSumm(userService.getUserByLogin(login).getId_user(), dateFrom,dateBefore);
    }

    @RequestMapping(value = {"/user/{login}/extract"},method = RequestMethod.GET)
    public ModelAndView extractPage(@PathVariable(value = "login") String login){
        List<Transaction> transactions = transactionService.getAllTransactions(userService.getUserByLogin(login).getId_user());
        ModelAndView modelAndView = new ModelAndView("extract","transactions",transactions);
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(){
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("admin","users",users);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/adduser",method = RequestMethod.GET)
    public ModelAndView addUserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adduser");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/adduser/create", method = RequestMethod.POST)
    public @ResponseBody Integer addUser(@RequestBody User user){
        return userService.addUser(user);

    }

    @RequestMapping(value = "/admin/{id_user}/addcheck", method = RequestMethod.GET)
    public ModelAndView addCheckPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addcheck");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/{id_user}/addcheck/create", method = RequestMethod.POST)
    public @ResponseBody Integer addCheck(@RequestBody Check check,@PathVariable(value = "id_user") Integer id_user){
        check.setId_user(id_user);
        return checkService.addCheck(check);
    }

    @RequestMapping(value = "/admin/{id_user}/deleteuser",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable(value = "id_user")Integer id_user){
        userService.deleteUser(id_user);
        return "redirect:/admin";
    }

}
