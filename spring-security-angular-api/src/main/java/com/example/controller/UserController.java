package com.example.controller;

import com.example.persistence.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by asheeshdwivedi on 1/16/16.
 */
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/findAllUser")
    public List<User> findAllUser()throws Exception {
        return userService.findAll();
    }

    @RequestMapping(path = "/findByEmail/{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(path = "/resetPassword/{key}", method = RequestMethod.POST)
    public void resetPassword(@PathVariable("key") String email , @RequestParam(value="password") String password)throws Exception {
        String[] parameter = email.split("\\|");
        String mailId = parameter[0];
        long expiryDate = 0;
        try {
            expiryDate = Long.parseLong(parameter[1], 10);
        } catch (NumberFormatException e) {
            throw e;
        }
        if(expiryDate < new Date().getTime()) {
            throw new RuntimeException("Forgot password link expired.");
        }
        User user =  userService.findByEmail(mailId);
        user.setPassword(password);
        userService.update(user);
    }

}
