package io.arkmusn.internship.domain.entity;

import javax.persistence.*;

/**
 * 角色权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/16
 */

@Entity
public class RolePermission extends AbstractPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    public RolePermission() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = id.longValue();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
