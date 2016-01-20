package com.example.service;

import com.example.AbstractTest;
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
        String email = "xxx@gmail.com";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";

        emailServiceImpl.send(email, subject, mailBody);
    }
}
