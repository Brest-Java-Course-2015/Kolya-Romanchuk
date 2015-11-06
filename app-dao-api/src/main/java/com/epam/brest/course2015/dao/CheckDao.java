package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Check;

import java.util.List;

/**
 * Created by user on 06.11.15.
 */
public interface CheckDao {

    public List<Check> getAllChecks();
    public Integer addCheck(Check user);
    public void deleteCheck(Integer id_check);
    public void updateCheck(Check check);
    public Check getCheckById(Integer id_check);
    public Check getCheckByCheckNumder(Integer cheknumber);

}
