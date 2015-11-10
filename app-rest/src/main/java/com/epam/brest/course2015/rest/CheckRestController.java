package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Check;
import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.service.CheckService;
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
public class CheckRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CheckService checkService;

    @RequestMapping(value = "/checks", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Check> getAllCheck(){
        LOGGER.debug("getAllCheck()");
        return checkService.getAllChecks();
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addCheck(@RequestBody Check check){
        LOGGER.debug("addCheck(): check_id = {} ",check.getId_transaction());
        return checkService.addCheck(check);
    }

    @RequestMapping(value = "/check/{number}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Check getCheckByCheckNumber(@PathVariable(value = "number") Integer number){
        LOGGER.debug("getCheckByCheckNumber(): CheckNumber= {} ",number);
        return checkService.getCheckByCheckNumder(number);
    }

    @RequestMapping(value = "/check/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Check getCheckById(@PathVariable(value = "id") Integer id){
        LOGGER.debug("getCheckById(): id = {} ",id);
        return checkService.getCheckById(id);
    }

    @RequestMapping(value = "/check/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteCheck(@RequestBody Integer id){
        LOGGER.debug("deleteCheck(): id = {} ",id);
        checkService.deleteCheck(id);
    }

    @RequestMapping(value = "/check/update",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody void updateCheck(@RequestBody Check check){
        LOGGER.debug("updateCheck(): check_id = {} ",check.getId_transaction());
        checkService.updateCheck(check);
    }

}
