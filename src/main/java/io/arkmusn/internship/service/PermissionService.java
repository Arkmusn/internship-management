package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.AbstractPermission;
import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.repository.RolePermissionRepository;
import io.arkmusn.internship.repository.UserPermissionRepository;
import io.arkmusn.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

@Service
public class PermissionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public Collection<String> getPermissionStrByUserId(Integer userId) {
        User user = userRepository.findOne(userId);
        List<AbstractPermission> userPermissions = userPermissionRepository.findByUserId(user.getId());
        Set<String> permissionStrSet = new HashSet<>(64);
        permissionStrSet.addAll(buildPermissionStrings(userPermissions));
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> {
            List<AbstractPermission> rolePermissions = rolePermissionRepository.findByRoleId(role.getId());
            permissionStrSet.addAll(buildPermissionStrings(rolePermissions));
        });
        return permissionStrSet;
    }

    /**
     * 构造权限字符串集合
     *
     * @param permissions 权限集合
     * @return 权限字符串集合
     */
    private Collection<String> buildPermissionStrings(Collection<AbstractPermission> permissions) {
        Set<String> set = new HashSet<>(64);
        permissions.forEach(permission -> set.add(buildPermissionString(permission)));
        return set;
    }

    /**
     * 构造单个权限字符串
     *
     * @param permission 权限
     * @return 权限字符串
     */
    private String buildPermissionString(AbstractPermission permission) {
        StringBuilder builder = new StringBuilder();
        builder.append(permission.getEntityType());
        builder.append(':');
        builder.append(permission.getEntityId());
        builder.append(':');
        builder.append(permission.getActionType());
        return builder.toString();
    }
}
