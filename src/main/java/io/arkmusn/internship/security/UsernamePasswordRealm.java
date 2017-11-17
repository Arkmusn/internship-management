package io.arkmusn.internship.security;

import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.service.PermissionService;
import io.arkmusn.internship.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

public class UsernamePasswordRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = userService.getUserByUsername(token.getPrincipal().toString());
        if (user.getPassword().equals(token.getCredentials().toString())) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = userService.getUserByUsername(principals.getPrimaryPrincipal().toString());
        Set<Role> roles = user.getRoles();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        roles.forEach(role -> authorizationInfo.addRole(role.getName()));
        authorizationInfo.addStringPermissions(permissionService.getPermissionStrByUserId(user.getId()));
        return authorizationInfo;
    }
}
