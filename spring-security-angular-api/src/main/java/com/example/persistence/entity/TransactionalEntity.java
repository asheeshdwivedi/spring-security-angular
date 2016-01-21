package com.example.persistence.entity;

import java.util.Date;

/**
 * Created by asheeshdwivedi on 1/18/16.
 */
public interface TransactionalEntity<E extends TransactionalEntity<E>> extends Entity<E> {

    void setVersion(Integer version);

    Integer getVersion();

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Date getCreatedAt();

    public void setCreatedAt(Date createdAt) ;

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

    public Date getUpdatedAt();

    public void setUpdatedAt(Date updatedAt);
}
