package com.example.service;

import com.example.persistence.entity.User;
import com.example.persistence.repositiories.UserRepository;
import com.example.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@Service
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), SecurityUtil.convertToGrantedAuthority(user.getAuthorities()));
    }
}
