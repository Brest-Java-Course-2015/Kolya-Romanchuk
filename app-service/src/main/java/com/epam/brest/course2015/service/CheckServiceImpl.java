package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.CheckDao;
import com.epam.brest.course2015.domain.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by user on 09.11.15.
 */
@Transactional
public class CheckServiceImpl implements CheckService {

    private static final Logger LOGGER = LogManager.getLogger();

    private CheckDao checkDao;

    public void setCheckDao (CheckDao checkDao){
        this.checkDao = checkDao;
    }

    @Override
    public List<Check> getAllChecks() {
        LOGGER.debug("getAllChecks()");
        return checkDao.getAllChecks();
    }

    @Override
    public Integer addCheck(Check check) {
        Assert.notNull(check, "Check should not be null");
        LOGGER.debug("addCheck(): checkNumber = {} ",check.getChecknumber());
        Assert.isNull(check.getId_check(), "Id Check should be null");
        Assert.notNull(check.getChecknumber(), "Check Number should not be null");
        Assert.notNull(check.getSumma(), "Summa Check should not be null");
        Assert.notNull(check.getId_user(),"Id_user Check should not be null");
        return checkDao.addCheck(check);
    }

    @Override
    public void deleteCheck(Integer id_check) {
        LOGGER.debug("deleteCheck(): id_check = {} ",id_check);
        Assert.notNull(id_check, "Id Check should not be null");
        Assert.isTrue(id_check > 0);
        checkDao.deleteCheck(id_check);
    }

    @Override
    public void updateCheck(Check check) {
        Assert.notNull(check, "Check should not be null");
        LOGGER.debug("updateCheck(): checkNumber = {} ",check.getChecknumber());
        Assert.notNull(check.getSumma(), "Summa Check should not be null");
        checkDao.updateCheck(check);
    }

    @Override
    public Check getCheckById(Integer id_check) {
        LOGGER.debug("getCheckById(): id_check = {} ", id_check);
        Assert.notNull(id_check, "Id Check should not be null");
        Assert.isTrue(id_check > 0);
        return checkDao.getCheckById(id_check);
    }

    @Override
    public Check getCheckByCheckNumder(Integer cheknumber) {
        LOGGER.debug("getCheckByCheckNumder(): checkNumber = {} ", cheknumber);
        Assert.notNull(cheknumber, "Check Number should not be null");
        return checkDao.getCheckByCheckNumder(cheknumber);
    }

    @Override
    public void logCheck(Check check) {
        LOGGER.debug("logCheck(): id_check = {}", check.getId_check());
    }
}
