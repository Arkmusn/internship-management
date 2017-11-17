package io.arkmusn.internship.config;

import io.arkmusn.internship.security.UsernamePasswordRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置类
 *
 * @author Arkmusn
 *         create 2017/11/17
 */

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager() {
        SecurityManager securityManager = new DefaultSecurityManager(realm());
        return securityManager;
    }

    @Bean
    public Realm realm() {
        return new UsernamePasswordRealm();
    }
}
