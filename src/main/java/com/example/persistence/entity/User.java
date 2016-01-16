package com.example.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */

@Entity
public class User extends AbstractEntity<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(// @formatter:off
        joinColumns =        { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID") }
    ) // @formatter:on
    private Set<Authority> authorities;

    public User() {
        super(User.class);
    }

    public User(final String email, final String password, final Set<Authority> authorities) {
        super(User.class);

        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public User(final String email, final String password, final Set<Authority> authorities, final String uuidToSet) {
        this(email, password, authorities);
    }


    // API

    public Long getId() {
        return id;
    }

    public void setId(final Long idToSet) {
        id = idToSet;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordToSet) {
        password = passwordToSet;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final Set<Authority> authorities) {
        this.authorities = authorities;
    }



}
