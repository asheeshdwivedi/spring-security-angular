package com.example.persistence.repositiories;

import com.example.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public interface UserRepository extends JpaRepository<User , Long>{

    User findByEmail(final String email);
}
