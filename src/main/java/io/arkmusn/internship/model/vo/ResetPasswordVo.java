package io.arkmusn.internship.model.vo;

/**
 * 重设密码VO
 *
 * @author Arkmusn
 *         create 2017/12/10
 */

public class ResetPasswordVo {
    private Integer id;
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
