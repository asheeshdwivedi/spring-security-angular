package com.example.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractTest;
import com.example.persistence.entity.Authority;

/**
 * Created by Twinkle.puri on 19-01-2016.
 */
@Transactional
public class AuthorityServiceTest extends AbstractTest {

    @Autowired
    private AuthorityService authorityService;

    @Before
    public void setUp(){super.setUp();}

    @Test
    public void testFindByNameIfExists(){
        String name = "ROLE_USER";
        Authority auth = authorityService.findByName(name);
        Assert.assertNotNull("failure - expected not null",auth);
        Assert.assertEquals("failure - expected auth.name match ", name, auth.getName());
    }

    @Test
    public void testFindByNameIfNull(){
        String name = "ROLE_TES";
        Authority auth = authorityService.findByName(name);
        Assert.assertNull("failure - expected null", auth);
    }


    @Test
    public void testFindByIdIfExists(){
        Long id = new Long(1);
        Authority auth = authorityService.findById(id);
        Assert.assertNotNull("Failure - expected not null", auth);
        Assert.assertEquals("failure- expected auth.id match", id,auth.getId());
    }

    @Test
    public void testFindByIdIfNull(){
        Long id = new Long(10000);
        Authority auth = authorityService.findById(id);
        Assert.assertNull("failure - expected null", auth);
    }

    @Test
    public void testCreate(){
        final Authority authoritie = new Authority("ROLE_TEST");
        Authority auth = authorityService.create(authoritie);
        Authority savedAuth = authorityService.findByName(auth.getName());
        Assert.assertNotNull("failure - expected not null",savedAuth);
        Assert.assertEquals("failure - expected auth.name match","ROLE_TEST", savedAuth.getName());
    }

    @Test
    public void testUpdateIfEntryExists(){
        String name = "ROLE_USER";
        Authority auth = authorityService.findByName(name);
        auth.setId((long) 100);
        authorityService.update(auth);
        Assert.assertNotNull("failure - expected not null", auth);
    }

    @Test
    public void testUpdateIfNull(){
        String name = "ROLE_U";
        Authority auth = authorityService.findByName(name);
        Assert.assertNull("failure - expected not null", auth);
    }
}
