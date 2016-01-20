package com.example.config;

import com.example.security.AuthenticationFailure;
import com.example.security.AuthenticationSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.annotation.Resource;

/**
 * Created by asheeshdwivedi on 1/14/16.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    private  AuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private  AuthenticationFailure authenticationFailure;

    @Autowired
    private  AuthenticationSuccess authenticationSuccess;

    @Autowired
    private UserDetailsService userService;






    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .formLogin()
                .successHandler(authenticationSuccess) //sets status to 200 OK
                .failureHandler(authenticationFailure).and().httpBasic().and().authorizeRequests()
                .antMatchers("/index.html","/forgotPassword","/send-mail/**/*" , "/" ,"/login" ,"/auth/*","/partials/login.html","/partials/common/*","/vendor/**/**/*").permitAll().anyRequest().authenticated();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userService);
    }


}
