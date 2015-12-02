package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Check;

import java.util.List;

/**
 * Created by user on 06.11.15.
 */
public interface CheckService {

    public List<Check> getAllChecks(Integer id_user);
    public Integer addCheck(Check check);
    public void deleteCheck(Integer id_check);
//    public void updateCheck(Check check);
    public Check getCheckById(Integer id_check);
    public Check getCheckByCheckNumder(Integer cheknumber);
//    public void logCheck(Check check);

}
