package com.example.model;

import java.util.List;
import java.util.Map;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public final class UserDetails {

    private final String userName;

    private final Map<String ,Boolean> roles;

    public UserDetails(String userName , Map<String ,Boolean> roles) {
        this.roles = roles;
        this.userName = userName;
    }

    public Map<String ,Boolean> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }
}
