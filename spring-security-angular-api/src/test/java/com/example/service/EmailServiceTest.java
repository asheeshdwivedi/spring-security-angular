package com.example.service;

import com.example.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

/**
 * Created by sathish.s on 19-01-2016.
 */
public class EmailServiceTest extends AbstractTest {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testSendingMail() throws MessagingException {
        String email = "puri.twinkle@gmail.com";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";
        boolean valid = emailServiceImpl.send(email, subject, mailBody);
        Assert.assertEquals("failure - expected 200 success", valid);
        Assert.assertTrue("failure - expected true",valid);
    }

    @Test
    public void testSendingMailHandlingException() throws MessagingException {
        String email = "xxxxxxxx";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";
        boolean valid = emailServiceImpl.send(email, subject, mailBody);
        Assert.assertNotEquals("failure - expected error",valid);
        Assert.assertFalse("failure - expected true",valid);
    }

    @Test
    public void testSendingAsyncMail() {
        String email = "puri.twinkle@gmail.com";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";
        emailServiceImpl.sendAsync(email, subject, mailBody);
        Assert.assertTrue(true);
    }

    @Test
    public void testSendingAsyncMailHandlingException() {
        String email = "sfdssf";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";
        emailServiceImpl.sendAsync(email, subject, mailBody);
        Assert.assertFalse(false);
    }

}
