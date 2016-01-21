package com.example.service;

import com.example.persistence.entity.Authority;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public interface AuthorityService {


    public Authority findByName(final String name);

    public Authority findById(final long id);

    public Authority create(final Authority entity);

    public void update(final Authority entity);
}
