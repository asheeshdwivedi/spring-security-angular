package com.example.persistence.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by asheeshdwivedi on 1/18/16.
 */

@MappedSuperclass
public abstract class AbstractTransactionalEntity<E extends TransactionalEntity<E>> extends AbstractEntity<E> implements TransactionalEntity<E>{

    protected AbstractTransactionalEntity(Class<E> type) {
        super(type);
    }


    @Version
    private Integer version;


    @NotNull
    private String createdBy;


    @NotNull
    private Date createdAt;


    private String updatedBy;


    private Date updatedAt;


    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @PrePersist
    public void beforePersist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername();
        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot persist a TransactionalEntity without a username "
                            + "in the RequestContext for this thread.");
        }
        setCreatedBy(username);

        setCreatedAt(new Date());
    }

    @PreUpdate
    public void beforeUpdate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername();
        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot update a TransactionalEntity without a username "
                            + "in the RequestContext for this thread.");
        }
        setUpdatedBy(username);

        setUpdatedAt(new Date());
    }

}
