package com.example.controller;

import com.example.persistence.entity.User;
import com.example.service.EmailService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by asheeshdwivedi on 1/19/16.
 */

@RestController
public class EmailController extends BaseController{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private static long HOURS_24_IN_MILLISECONDS = (60*60*24*1000);
    private static String FORGOTPASSWORD_URL = "http://localhost:8080/#/resetPassword/";
    @RequestMapping(path = "/send-mail/{email}/" , method = RequestMethod.POST)
    public void sendMail(@PathVariable("email") String email , @RequestParam(
            value="wait"
            ,defaultValue = "false") boolean waitForAsyncResult) throws ExecutionException, InterruptedException {
        logger.info(">Send");
        User user = userService.findByEmail(email);

        if(user == null){
            throw new RuntimeException("User with email " + email + " does not exit ");
        }

        String key = email+"|"+(new Date().getTime()+HOURS_24_IN_MILLISECONDS);

        if(waitForAsyncResult){
            Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(email , "Forget Password" ,FORGOTPASSWORD_URL+key);
            boolean emailSent = asyncResponse.get();
            logger.info("-  email sent ? {}", emailSent);
        }else{
            emailService.sendAsync(email , "Forget Password" ,FORGOTPASSWORD_URL+key);
        }
    }



}
