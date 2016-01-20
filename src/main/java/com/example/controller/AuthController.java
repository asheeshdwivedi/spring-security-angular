package com.example.controller;

import com.example.config.SecurityConfiguration;
import com.example.security.SecurityUtil;
import com.example.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@RestController
public class AuthController extends BaseController{
    @Autowired
    private SecurityConfiguration securityConfig;


    @RequestMapping(path = "/getLoggedInUserDetails")
    public com.example.model.UserDetails getLoggedInUserDetails(HttpServletResponse httpServletResponse)throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        UserDetails userDetails = (UserDetails) principal;


        return new com.example.model.UserDetails(userDetails.getUsername(), SecurityUtil.convertToRoles(userDetails.getAuthorities()));
    }


    @RequestMapping(path="/auth/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }


}