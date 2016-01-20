package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by asheeshdwivedi on 1/17/16.
 */


@Component
public class EmailServiceImpl implements EmailService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendAsync(String to, String subject, String body) {
        logger.info("> sendAsync");
        try {
            this.send(to,subject,body);
        } catch (Exception e) {
            logger.warn("Exception caught sending asynchronous mail.", e);
        }
        logger.info("< sendAsync");
    }

    @Async
    @Override
    public Future<Boolean> sendAsyncWithResult(String to, String subject, String body) {
        logger.info("> sendAsyncWithResult");
        CompletableFuture<Boolean>  respose = new CompletableFuture<>();
        try{
            Boolean success = this.send(to,subject,body);
            respose.complete(success);
        }catch (Exception e){
            logger.warn("Exception caught sending asynchronous mail.", e);
            respose.completeExceptionally(e);
        }
        logger.info("< sendAsyncWithResult");
        return respose;
    }

    public Boolean send(String to , String subject, String body)  {
        logger.info("> send");
        Boolean success = Boolean.FALSE;
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);
            javaMailSender.send(message);
            success = Boolean.TRUE;
        }catch (Exception e){
            logger.warn("Exception caught sending  mail.", e);
        }
        logger.info("> send");
       return success;
    }
}
