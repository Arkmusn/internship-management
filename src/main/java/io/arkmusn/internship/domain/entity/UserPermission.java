package io.arkmusn.internship.domain.entity;

import javax.persistence.*;

/**
 * 用户权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/16
 */

@Entity
public class UserPermission extends AbstractPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    public UserPermission() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = id.longValue();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
