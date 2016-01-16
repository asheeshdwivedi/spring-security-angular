package com.example.security;

import com.example.persistence.entity.Authority;
import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.*;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public final class SecurityUtil {

    private SecurityUtil(){ throw new AssertionError();}


    public static final List<GrantedAuthority>  convertToGrantedAuthority(final Set<Authority> authorities){
        final Iterable<String> authorityNames = Iterables.transform(authorities, Functions.toStringFunction());
        final String[] arrayOfAuthorityNames = Iterables.toArray(authorityNames, String.class);
        final List<GrantedAuthority> authoritiesForSpring = AuthorityUtils.createAuthorityList(arrayOfAuthorityNames);
        return authoritiesForSpring;
    }

    public static final Map<String ,Boolean>  convertToRoles(final Collection<? extends GrantedAuthority> authoritiesForSpring){
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : authoritiesForSpring) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }
        return roles;
    }

}
