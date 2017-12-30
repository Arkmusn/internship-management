package io.arkmusn.internship.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 角色权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/16
 */

@Entity
public class RolePermission extends AbstractPermission {
    @ManyToOne
    private Role role;

    public RolePermission() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
