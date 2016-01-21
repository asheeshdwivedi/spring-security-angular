package com.example.service;

import javax.mail.MessagingException;
import java.util.concurrent.Future;

/**
 * Created by asheeshdwivedi on 1/19/16.
 */
public interface EmailService {

    public Boolean send(String to , String subject, String body);


    public void sendAsync(String to , String subject, String body);


    public Future<Boolean> sendAsyncWithResult(String to , String subject, String body);
}
