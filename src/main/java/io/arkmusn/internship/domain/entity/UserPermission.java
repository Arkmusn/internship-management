package io.arkmusn.internship.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 用户权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/16
 */

@Entity
public class UserPermission extends AbstractPermission {

    @ManyToOne
    private User user;

    public UserPermission() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
