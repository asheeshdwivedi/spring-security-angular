package com.example.controller;

import com.example.service.EmailService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @RequestMapping(path = "/send-mail/{email}/" , method = RequestMethod.POST)
    public void sendMail(@PathVariable("email") String email , @RequestParam(
            value="wait"
            ,defaultValue = "false") boolean waitForAsyncResult) throws ExecutionException, InterruptedException {

        logger.info(">Send");

        if(waitForAsyncResult){
            Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(email , "Forget Password" ,"Test Mail");
            boolean emailSent = asyncResponse.get();
            logger.info("-  email sent ? {}", emailSent);
        }else{
            emailService.sendAsync(email , "Forget Password" ,"Test Mail");
        }
    }



}
