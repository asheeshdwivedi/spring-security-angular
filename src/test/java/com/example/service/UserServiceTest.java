package com.example.service;

import com.example.AbstractTest;
import com.example.persistence.entity.Authority;
import com.example.persistence.entity.User;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by asheeshdwivedi on 1/18/16.
 */
@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Before
    public void setUp(){super.setUp();}


    @Test
    public void testFindByEmail(){
        String email = "admin1@xxx.com";
        User user =  userService.findByEmail(email);
        Assert.assertNotNull("failure - expected not null", user);
        Assert.assertEquals("failure - expected user.email match", email,  user.getEmail());

    }

    @Test
    public void testFindById() {

        Long id = new Long(1);
        User user =  userService.findById(id);
        Assert.assertNotNull("failure - expected not null", user);
        Assert.assertEquals("failure - expected user.id match", id,
                user.getId());
    }

    @Test
    public void testCreate(){

        final Authority authoritie = new Authority("ROLE_TES");

        authorityService.create(authoritie);

        User entity = new User("test@email.com", "test", Sets.<Authority> newHashSet(authoritie) , "test" , "test");

        User createdUser = userService.create(entity);

        Assert.assertNotNull("failure - expected entity not null",
                createdUser);
        Assert.assertNotNull("failure - expected entity.id not null",
                createdUser.getId());
        Assert.assertEquals("failure - expected entity.eail match", "test@email.com",
                createdUser.getEmail());


        Collection<User> users = userService.findAll();

        Assert.assertEquals("failure - expected 4 users", 4,
                users.size());
    }




}
