package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.AbstractPermission;
import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.domain.entity.UserPermission;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.repository.RolePermissionRepository;
import io.arkmusn.internship.repository.UserPermissionRepository;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    private UserRepository userRepository;
    private UserPermissionRepository userPermissionRepository;
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    public PermissionService(UserRepository userRepository, UserPermissionRepository userPermissionRepository, RolePermissionRepository rolePermissionRepository) {
        this.userRepository = userRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    /**
     * 通过用户ID获取其所有权限
     *
     * @param userId 用户ID
     * @return 其所有权限
     */
    public Collection<String> getPermissionStringByUserId(Integer userId) {
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
        permissions.forEach(permission -> set.add(PermissionUtils.getPermissionString(new String[]{permission.getEntityType().toString(), permission.getEntityId(), permission.getActionType().toString()})));
        return set;
    }

    public boolean savePermissionForUser(Permission permission, Integer userId) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户ID:" + userId + "不存在");
        UserPermission userPermission = new UserPermission();
        userPermission.setUser(user);
        userPermission.setEntityType(permission.getEntityType());
        userPermission.setEntityId(permission.getEntityId());
        userPermission.setActionType(permission.getActionType());
        return userPermissionRepository.save(userPermission) != null;
    }
}
