package io.arkmusn.internship.model.vo;

import io.arkmusn.internship.domain.entity.User;

/**
 * @author Arkmusn
 *         create 2018/4/11
 */

public class UserInfoVO {
    private String name;
    private User user;

    public UserInfoVO(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
