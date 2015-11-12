package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 06.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class CheckDaoImplTest {

    private final Logger LOGGER = LogManager.getLogger();

    private final Integer CHECKNUMBER = 1234;

    private final Integer SUMMA = 2134456;

    private final Check check = new Check(null, 13454897, 123443, 2);

    @Autowired CheckDao checkDao;

    @Test
    public void testGetAllChecks() throws Exception {
        LOGGER.debug("test: getAllChecks");
        List<Check> checks = checkDao.getAllChecks();
        assertTrue(checks.size() == 2);
    }

    @Test
    public void testAddCheck() throws Exception {
        LOGGER.debug("test: AddCheck");
        Integer id_check = checkDao.addCheck(check);
        assertNotNull(id_check);
        Check newCheck = checkDao.getCheckById(id_check);
        assertEquals(check.getChecknumber(), newCheck.getChecknumber());
        assertEquals(check.getSumma(), newCheck.getSumma());

    }

    @Test
    public void testDeleteCheck() throws Exception {
        LOGGER.debug("test: deleteCheck");
        List<Check> checks = checkDao.getAllChecks();
        Integer checkSize = checks.size();
        assertTrue(checks.size() > 0);
        checkDao.deleteCheck(checks.get(0).getId_check());
        assertTrue(checkSize - 1 == checkDao.getAllChecks().size());
    }

    @Test
    public void testUpdateCheck() throws Exception {
        LOGGER.debug("test: updateCheck");
        Check check = checkDao.getCheckByCheckNumder(CHECKNUMBER);
        check.setSumma(SUMMA + 1564);
        checkDao.updateCheck(check);
        Check newCheck = checkDao.getCheckByCheckNumder(CHECKNUMBER);
        assertEquals(check.getSumma(),newCheck.getSumma());
    }

}