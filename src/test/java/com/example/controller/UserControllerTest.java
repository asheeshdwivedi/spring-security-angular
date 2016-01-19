package com.example.controller;

import com.example.AbstractControllerTest;
import com.example.persistence.entity.User;
import com.example.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sathish.s on 19-01-2016.
 */
public class UserControllerTest extends AbstractControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(userController);
    }



    /*@Test
    public void testFindAllUser()throws Exception {
        String uri = "/findAllUser";
        when(userService.findAll()).then
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        System.out.println("status  " + status + " :: " + content);
        //UserDetails userDetails = super.mapFromJson(content, UserDetails.class);
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        //Assert.assertEquals("failure - expected entity.eail match", ADMIN_USER, userDetails.getUserName());


    }*/

    private Collection<User> getEntityListStubData() {
        Collection collection = new ArrayList<>();
        collection.add(getEntityStubData());
        return collection;
    }
    private User getEntityStubData() {
        User user = new User();
        user.setEmail("admin1@xxx.com");
        return user;
    }
}
