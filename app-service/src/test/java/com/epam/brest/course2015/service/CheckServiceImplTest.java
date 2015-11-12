package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 09.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class CheckServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Check check = new Check(null, 134879, 46554654, 2);

    private final Integer CHEKNUMBER = 1234;

    @Autowired
    private CheckService checkService;

    @Test
    public void testGetAllChecks() throws Exception {
        LOGGER.debug("test: getAllChecks()");
        checkService.getAllChecks();
    }

    @Test
    public void testAddCheck() throws Exception {
        LOGGER.debug("test: addCheck()");
        Integer countCheck = checkService.getAllChecks().size();
        checkService.addCheck(check);
        assertTrue(countCheck + 1 == checkService.getAllChecks().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void AddNullCheck() throws Exception{
        LOGGER.debug("test: addNullCheck()");
        checkService.addCheck(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddCheckNotNullId() throws Exception{
        LOGGER.debug("test: addCheckNotNullId()");
        Check check =  new Check();
        check.setId_check(1);
        checkService.addCheck(check);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddCheckNullCheckNumber() throws Exception{
        LOGGER.debug("test: addCheckNullCheckNumber()");
        Check check =  new Check();
        check.setSumma(154654);
        checkService.addCheck(check);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddCheckNullSumma() throws Exception{
        LOGGER.debug("test: addCheckNullSumma()");
        Check check =  new Check();
        check.setChecknumber(154654);
        checkService.addCheck(check);
    }



    @Test
    public void testUpdateCheck() throws Exception {
        LOGGER.debug("test: updateCheck()");
        Check check = checkService.getCheckByCheckNumder(CHEKNUMBER);
        check.setSumma(2012);
        checkService.updateCheck(check);
        assertEquals(check.getSumma(), checkService.getCheckByCheckNumder(CHEKNUMBER).getSumma());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCheckNullCheckNumber() throws Exception{
        LOGGER.debug("test: updateCheckNullCheckNumber()");
        Check check = checkService.getCheckByCheckNumder(CHEKNUMBER);
        check.setSumma(null);
        checkService.updateCheck(check);
        assertEquals(check.getSumma(), checkService.getCheckByCheckNumder(CHEKNUMBER).getSumma());
    }

}