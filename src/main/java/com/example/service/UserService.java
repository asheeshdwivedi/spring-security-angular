package com.example.service;


import com.example.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */

public interface UserService extends UserDetailsService{


    public User findByEmail(final String email);

    public User findById(final long id);

    public User create(final User entity);

    public void update(final User entity);

}
