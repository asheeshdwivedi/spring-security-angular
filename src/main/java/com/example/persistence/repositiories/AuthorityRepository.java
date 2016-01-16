package com.example.persistence.repositiories;

import com.example.persistence.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asheeshdwivedi on 1/15/16.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(final String name);
}
