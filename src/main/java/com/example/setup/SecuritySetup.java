package com.example.setup;

import com.example.persistence.entity.Authority;
import com.example.persistence.entity.User;
import com.example.service.AuthorityService;
import com.example.service.UserService;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@Component
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {


    private boolean setupDone;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ApplicationContext eventPublisher;

    static final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");

            createAuthorities();
            createUsers();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // Authority

    private void createAuthorities() {
        createAuthorityIfNotExisting("ROLE_ADMIN");
        createAuthorityIfNotExisting("ROLE_USER");
    }

    final void createAuthorityIfNotExisting(final String name) {
        final Authority entityByName = authorityService.findByName(name);
        if (entityByName == null) {
            final Authority entity = new Authority(name);
            authorityService.create(entity);
        }
    }

    // Principal/User

    final void createUsers() {
        final Authority authorityUser = authorityService.findByName("ROLE_USER");
        final Authority authorityAdmin = authorityService.findByName("ROLE_ADMIN");

        createUserIfNotExisting(SecurityConstants.ADMIN1_EMAIL, SecurityConstants.ADMIN1_PASS, Sets.<Authority> newHashSet(authorityUser, authorityAdmin));
        createUserIfNotExisting(SecurityConstants.ADMIN2_EMAIL, SecurityConstants.ADMIN2_PASS, Sets.<Authority> newHashSet(authorityUser, authorityAdmin));
        createUserIfNotExisting(SecurityConstants.USER1_EMAIL, SecurityConstants.USER1_PASS, Sets.<Authority> newHashSet(authorityUser));
    }

    final void createUserIfNotExisting(final String username, final String pass, final Set<Authority> authorities) {
        final User entityByName = userService.findByEmail(username);
        if (entityByName == null) {
            final User entity = new User(username, pass, authorities);
            userService.create(entity);
        }
    }

}
