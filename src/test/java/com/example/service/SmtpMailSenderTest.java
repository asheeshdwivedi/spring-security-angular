package com.example.service;

import com.example.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sathish.s on 19-01-2016.
 */
public class SmtpMailSenderTest extends AbstractTest {
    @Autowired
    private SmtpMailSender smtpMailSender;

    @Before
    public void setUp(){super.setUp();}

    @Test
    public void testSendingMail() {
        String email = "sathish.257216@gmail.com";
        String subject = "Forgot Password";
        String mailBody = "http://localhost:8080/#/login";

        //smtpMailSender.send(email, subject, mailBody);
    }
}
