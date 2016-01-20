package com.example.service;

import com.example.persistence.entity.Authority;
import com.example.persistence.repositiories.AuthorityRepository;
import com.example.persistence.repositiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;

    }
    @Override
    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public Authority findById(long id) {
        return authorityRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Authority create(Authority entity) {
        return authorityRepository.save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Authority entity) {
        authorityRepository.save(entity);
    }
}
