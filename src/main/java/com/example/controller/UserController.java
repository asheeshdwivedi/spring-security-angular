package com.example.controller;

import com.example.persistence.entity.User;
import com.example.security.SecurityUtil;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(path = "/resetPassword/{email}", method = RequestMethod.POST)
    public void resetPassword(@PathVariable("email") String email , @RequestParam(value="password") String password)throws Exception {
        User user =  userService.findByEmail(email);
        user.setPassword(password);
        userService.update(user);
    }

}
