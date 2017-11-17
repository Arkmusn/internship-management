package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn on 2017/11/17.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
