package com.springapp.mvc.controller;

import com.springapp.mvc.model.TalkyTalkyResponse;
import com.springapp.mvc.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class EchoControllerTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void headGet() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk()).andReturn();

        final String response = mvcResult.getResponse().getContentAsString();
        String jsonPayload = "{\"status\":\"ack\",\"message\":\"alive\"}";
        TestUtil.assertEqualsJsonStrings(response, jsonPayload, TalkyTalkyResponse.class);
    }

    @Test
    public void sendEchoPost() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(
                post("/sendecho")
                        .content("{\"target\":\"Dean\", \"message\":\"hello\"}")
                        .contentType(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        final String response = mvcResult.getResponse().getContentAsString();
        String jsonPayload = "{\"status\":\"ack\",\"message\":\"Dean says hello\"}";
        TestUtil.assertEqualsJsonStrings(response, jsonPayload, TalkyTalkyResponse.class);
    }

}
