package io.arkmusn.internship.util;

import io.arkmusn.internship.model.bo.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author Arkmusn
 *         create 2017/11/20
 */

public abstract class PermissionUtils {
    public static void checkPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(permission);
    }

    public static void checkPermission(Permission permission) {
        PermissionUtils.checkPermission(permission.getEntityType() + ":" + permission.getEntityId() + ":" + permission.getActionType());
    }
}
