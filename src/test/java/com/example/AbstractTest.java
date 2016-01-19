package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringSecurityAngularApplication.class)
@WebAppConfiguration
@Profile("hsqldb")
public abstract class AbstractTest {


	public static final String ADMIN_USER = "admin1@xxx.com";
	public static final String ADMIN_PWD = "admin1Pass";
	public static Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority[]{ new SimpleGrantedAuthority("ROLE_ADMIN")});

	protected Logger logger = LoggerFactory.getLogger(this.getClass());



	protected  void setUp(){
		final Authentication authToken = new UsernamePasswordAuthenticationToken(new User(ADMIN_USER , ADMIN_PWD ,authorities) , ADMIN_PWD , authorities);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}



}
