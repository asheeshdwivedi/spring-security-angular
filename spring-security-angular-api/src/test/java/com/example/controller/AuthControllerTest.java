package com.example.controller;

import com.example.AbstractControllerTest;
import com.example.config.SecurityConfiguration;
import com.example.model.UserDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asheeshdwivedi on 1/18/16.
 */
@Transactional
public class AuthControllerTest extends AbstractControllerTest {

    @Mock
    private SecurityConfiguration securityConfig;

    @InjectMocks
    private AuthController authController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(authController);
    }

    @Test
    public void logout()throws Exception {

        String uri = "/auth/logout";

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);

        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);


    }

    @Test
    public void getLoggedInUserDetails()throws Exception {
        String uri = "/getLoggedInUserDetails";

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        UserDetails userDetails = super.mapFromJson(content, UserDetails.class);
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertEquals("failure - expected entity.eail match", ADMIN_USER,
                userDetails.getUserName());


    }



}
