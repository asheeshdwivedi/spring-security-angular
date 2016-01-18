package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by asheeshdwivedi on 1/17/16.
 */


@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to , String subject, String body) throws MessagingException {
        MimeMessage mimeMessageHelper = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessageHelper ,true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body ,true);
        javaMailSender.send(mimeMessageHelper);
    }
}
