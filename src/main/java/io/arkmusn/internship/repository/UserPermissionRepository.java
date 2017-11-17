package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.AbstractPermission;
import io.arkmusn.internship.domain.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    public List<AbstractPermission> findByUserId(Integer userId);
}
