package com.example.persistence.entity;



import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * Created by asheeshdwivedi on 1/14/16.
 */
@MappedSuperclass
public abstract class AbstractEntity<E extends Entity<E>> implements Entity<E> {


    /**
     *
     */
    private static final long serialVersionUID = -4378019247086085314L;


    @Transient
    private Class<E> type;


    @Version
    private Integer version;


    protected AbstractEntity(Class<E> type) {
        Assert.notNull(type);
        this.type = type;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }


    @Override
    public Integer getVersion() {
        return version;
    }


    @Override
    @Transient
    public Class<E> getEntityType() {
        return this.type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (getClass() != obj.getClass())) {
            return false;
        }
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        if (getId() == null && other.getId() == null) {
            return this == other;
        }
        return ObjectUtils.nullSafeEquals(getId(), other.getId());
    }


}
