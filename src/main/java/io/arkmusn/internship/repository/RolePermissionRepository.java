package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.AbstractPermission;
import io.arkmusn.internship.domain.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Arkmusn on 2017/11/17.
 */

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    public List<AbstractPermission> findByRoleId(Integer roleId);
}
