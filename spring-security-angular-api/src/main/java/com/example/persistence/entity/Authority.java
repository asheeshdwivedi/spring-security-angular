package com.example.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
@Entity
public class Authority extends AbstractEntity<Authority> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Authority() {
        super(Authority.class);
    }

    public Authority(final String nameToSet) {
        super(Authority.class);
        name = nameToSet;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long idToSet) {
        id = idToSet;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }


    @Override
    public String toString() {
        return this.name;
    }

}
