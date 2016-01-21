package com.example.model;

import java.util.List;
import java.util.Map;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public  class UserDetails {

    private String userName;

    private Map<String ,Boolean> roles;

    public UserDetails(){}

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
