package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.rest.UserRestController;
import com.epam.brest.course2015.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
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

import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by user on 10.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class UserControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private UserRestController userRestController;

    @Autowired
    private UserService userService;


    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(userRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }


    @After
    public void clean(){
        verify(userService);
        reset(userService);
    }


    @Test
    public void testGetAllUsers() throws Exception {
        expect(userService.getAllUsers()).andReturn(Arrays.<User>asList(new User(1,"l","p","f","l")));
        replay(userService);
        LOGGER.debug("test: getAllUsers()");
        mockMvc.perform(
                get("/users")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUer() throws Exception{
        expect(userService.addUser(anyObject(User.class))).andReturn(2);
        replay(userService);
        LOGGER.debug("test: addUser()");

        String user = new ObjectMapper().writeValueAsString(new User(1,"l","p","f","l"));

        mockMvc.perform(post("/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(user)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetUserByLogin() throws Exception{
        expect(userService.getUserByLogin(anyObject(String.class))).andReturn(new User(1, "l", "p", "f", "l"));
        replay(userService);
        LOGGER.debug("test: getUserByLogin()");
        mockMvc.perform(get("/user/l").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception{
        expect(userService.getUserById(anyObject(Integer.class))).andReturn(new User(1, "l", "p", "f", "l"));
        replay(userService);
        LOGGER.debug("test: getUserById()");
        mockMvc.perform(get("/user/id/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }



}