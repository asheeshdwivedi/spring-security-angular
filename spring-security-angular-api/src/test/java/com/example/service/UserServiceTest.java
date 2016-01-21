package com.example.service;

import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractTest;
import com.example.persistence.entity.Authority;
import com.example.persistence.entity.User;
import com.google.common.collect.Sets;

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
    public void testFindByEmailNotExisting(){
        String email = "admin123@xyz.com";
        User user =  userService.findByEmail(email);
        Assert.assertNull("failure - not present", user);

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
    public void testFindByIdWhenNull() {
        Long id = new Long(1000);
        User user =  userService.findById(id);
        Assert.assertNull("failure - expected null", user);
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

    @Test
    public void testUpdateUponCreateUser(){
        final Authority authoritie = new Authority("ROLE_TES");
        authorityService.update(authoritie);
        User entity = new User("test@email.com", "test", Sets.<Authority> newHashSet(authoritie) , "test" , "test");
        userService.create(entity);
        entity = userService.findByEmail(entity.getEmail());
        entity.setFirstName("test123");
        userService.update(entity);
        User savedUser = userService.findByEmail(entity.getEmail());

        Assert.assertEquals("test123", savedUser.getFirstName());
        Assert.assertNotNull("entity.id should not be null", entity.getId());
    }

    @Test
    public void testUpdateExistingUser(){
        Long id = new Long(1);
        User user =  userService.findById(id);
        user.setFirstName("user1");
        userService.update(user);
        User savedUser = userService.findById(user.getId());
        Assert.assertEquals("user1", savedUser.getFirstName());
        Assert.assertNotNull("entity.id should not be null", user.getId());

    }

    @Test
    public void testUpdateIfNull(){
        Long id = new Long(19999);
        User user =  userService.findById(id);
        Assert.assertNull("Failure- expected Null", user);

    }


    @Test
    public void testLoadUserByUsername(){
        String username = "admin1@xxx.com";
        org.springframework.security.core.userdetails.UserDetails user= userService.loadUserByUsername(username);
        Assert.assertNotNull("failure - expected Not Null", user);
        Assert.assertEquals("failure - expected user.email match", username, user.getUsername());

    }

    @Test (expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameIfNull(){
        String username = "admin1@xyz.com";
        org.springframework.security.core.userdetails.UserDetails user =userService.loadUserByUsername(username);
        Assert.assertNull(user);

    }

    @Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        Assert.assertNotNull("failure - expected Not Null",users);
        //Assert.assertEquals("failure - expected 4 users", 4, users.size());
    }


}

