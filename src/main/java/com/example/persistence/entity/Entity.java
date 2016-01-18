package com.example.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Created by asheeshdwivedi on 1/14/16.
 */

public interface Entity<E extends Entity<E>> extends Serializable {


    <T extends Serializable> T getId();

    Class<E> getEntityType();


}

