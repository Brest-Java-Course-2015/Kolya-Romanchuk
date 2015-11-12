package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.Transaction;
import com.epam.brest.course2015.service.TransactionService;
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
@ContextConfiguration(locations = {"classpath:test-spring-rest-mock.xml"})
public class TransactionControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private TransactionRestController transactionRestController;

    @Autowired
    private TransactionService transactionService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(transactionRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void clean(){
        verify(transactionService);
        reset(transactionService);
    }

    @Test
    public void testGetAllTransactions() throws Exception{
        expect(transactionService.getAllTransactions())
                .andReturn(Arrays.<Transaction>asList(new Transaction(1, 12345, 4564564, 1213213, null,2)));
        replay(transactionService);
        LOGGER.debug("test: getAllTransactions()");

        mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTransaction() throws Exception{
        expect(transactionService.addTransaction(anyObject(Transaction.class))).andReturn(2);
        replay(transactionService);
        LOGGER.debug("test: addTransaction()");

        String transaction = new ObjectMapper().writeValueAsString(new Transaction(1,123,1546,1564,null, 2));

        mockMvc.perform(post("/transaction").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(transaction)).andDo(print())
                .andExpect(status().isCreated()).andExpect(content().string("2"));
    }

    @Test
    public void testGetTransactionById() throws Exception{
        expect(transactionService.getTransactionById(anyInt())).andReturn(new Transaction(1,132415,1231,1231234,null, 2));
        replay(transactionService);
        LOGGER.debug("test: getTransactionById()");

        mockMvc.perform(get("/transaction/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

}
