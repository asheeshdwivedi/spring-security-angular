package com.example.controller;

import com.example.config.SecurityConfiguration;
import com.example.security.SecurityUtil;
import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@RestController
public class AuthController {



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