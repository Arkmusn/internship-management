package io.arkmusn.internship.security;

import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.service.PermissionService;
import io.arkmusn.internship.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

@Component
public class UsernamePasswordRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UsernamePasswordRealm.class);

    private UserService userService;
    private PermissionService permissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = String.valueOf((char[]) token.getCredentials());
        if (username == null)
            throw new UnknownAccountException();
        if (password == null)
            throw new IncorrectCredentialsException();

        User user = userService.getUserByUsername(username);
        String HashedPassword = new Sha256Hash(password).toHex();
        if (user.getPassword().equals(HashedPassword)) {
            return new SimpleAuthenticationInfo(user.getUsername(), password, this.getName());
        }
        else
            throw new IncorrectCredentialsException();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = userService.getUserByUsername(principals.getPrimaryPrincipal().toString());
        Set<Role> roles = user.getRoles();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            authorizationInfo.addRole(role.getName());
        }
        authorizationInfo.addStringPermissions(permissionService.getPermissionStringByUserId(user.getId()));
        return authorizationInfo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
