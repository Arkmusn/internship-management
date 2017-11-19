package io.arkmusn.internship.security;

import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.service.PermissionService;
import io.arkmusn.internship.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

public class UsernamePasswordRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UsernamePasswordRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        if (username == null)
            throw new UnknownAccountException();
        if (password == null)
            throw new IncorrectCredentialsException();

        User user = userService.getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
        }
        else
            throw new IncorrectCredentialsException();
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
