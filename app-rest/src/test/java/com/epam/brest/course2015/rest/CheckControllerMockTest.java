package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Check;
import com.epam.brest.course2015.service.CheckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by user on 10.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-spring-rest-mock.xml"})
public class CheckControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private CheckRestController checkRestController;

    @Autowired
    private CheckService checkService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(checkRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @After
    public void clean(){
        verify(checkService);
        reset(checkService);
    }

    @Test
    public void testGetAllChecks() throws Exception{
        expect(checkService.getAllChecks()).andReturn(Arrays.<Check>asList(new Check(1,12413,45353,1)));
        replay(checkService);
        LOGGER.debug("test: getAllChecks()");

        mockMvc.perform(get("/checks").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCheck() throws Exception{
        expect(checkService.addCheck(anyObject(Check.class))).andReturn(2);
        replay(checkService);
        LOGGER.debug("test: addCheck()");

        String check = new ObjectMapper().writeValueAsString(new Check(1,232324,243234,1));

        mockMvc.perform(post("/check").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(check)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetCheckByCheckNumber() throws Exception{
        expect(checkService.getCheckByCheckNumder(anyObject(Integer.class))).andReturn(new Check(1, 15, 4564, 1));
        replay(checkService);
        LOGGER.debug("test: getCheckByCheckNumber()");

        mockMvc.perform(get("/check/15").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetCheckById() throws Exception{
        expect(checkService.getCheckById(anyObject(Integer.class))).andReturn(new Check(1,15,4564,1));
        replay(checkService);
        LOGGER.debug("test: getCheckById()");

        mockMvc.perform(get("/check/id/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

}
