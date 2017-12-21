package io.arkmusn.internship.util;

import io.arkmusn.internship.model.bo.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author Arkmusn
 *         create 2017/11/20
 */

public abstract class PermissionUtils {
    public static void checkPermission(String... PermissionStrings) {
        String string = getPermissionString(PermissionStrings);
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(string);
    }

    public static void checkPermission(Permission permission) {
        PermissionUtils.checkPermission(permission.getEntityType().toString().toLowerCase(), permission.getEntityId(), permission.getActionType().toString().toLowerCase());
    }

    public static void savePermission(Permission permission) {

    }

    public static void savePermission(String... PermissionStrings) {

    }

    public static String getPermissionString(String[] PermissionStrings) {
        StringBuilder builder = new StringBuilder();
        for (String s : PermissionStrings) {
            if (StringUtils.isEmpty(s))
                continue;
            s = s.toLowerCase();
            builder.append(s);
            builder.append(':');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
