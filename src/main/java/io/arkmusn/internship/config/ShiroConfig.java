package io.arkmusn.internship.config;

import io.arkmusn.internship.security.UsernamePasswordRealm;
import io.arkmusn.internship.security.filter.AjaxAuthenticationFilter;
import io.arkmusn.internship.security.filter.AjaxLogoutFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author Arkmusn
 *         create 2017/11/17
 */

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login/signOut", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("authc", ajaxAuthenticationFilter());
        filterMap.put("logout", ajaxLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/login/signIn");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(Realm realm) {
        SecurityManager securityManager = new DefaultWebSecurityManager(realm);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    public Realm realm() {
        return new UsernamePasswordRealm();
    }

    /**
     * 自定义登录验证过滤器
     *
     * @return ajaxAuthenticationFilter
     */
    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() {
        return new AjaxAuthenticationFilter();
    }

    /**
     * 自定义登出过滤器
     *
     * @return ajaxLogoutFilter
     */
    @Bean
    public AjaxLogoutFilter ajaxLogoutFilter() {
        return new AjaxLogoutFilter();
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean
     * (DefaultAdvisorAutoProxyCreator(可选)和
     * AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @link https://shiro.apache.org/spring.html
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Springboot 先加载了我们自定义的 Filter，然后再加载了 ShiroFilter
     * <p>
     * 解决方法:
     * 在自定义的filter里加上下面的代码
     *
     * @param ajaxAuthenticationFilter ajaxAuthenticationFilter
     * @return filterRegistrationBean
     * @link http://heeexy.com/2017/10/22/build-springboot-shiro-vue/
     */
    @Bean
    public FilterRegistrationBean ajaxAuthenticationFilterRegistrationBean(AjaxAuthenticationFilter ajaxAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(ajaxAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public FilterRegistrationBean ajaxLogoutFilterFilterRegistrationBean(AjaxLogoutFilter ajaxLogoutFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(ajaxLogoutFilter);
        registration.setEnabled(false);
        return registration;
    }
}
