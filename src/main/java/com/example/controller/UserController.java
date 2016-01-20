package com.example.controller;

import com.example.persistence.entity.User;
import com.example.security.SecurityUtil;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/resetPassword", method = RequestMethod.POST)
    public void updatePassword(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String emailId = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByEmail(emailId);
        System.out.println("request "+emailId+" :: "+password+ " ::: "+user.getEmail()+ ":::  "+user.getPassword());
        user.setPassword(password);
        userService.update(user);
    }

}
