package io.arkmusn.internship;

import io.arkmusn.internship.security.filter.AjaxAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipApplication.class, args);
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
    public FilterRegistrationBean filterRegistrationBean(AjaxAuthenticationFilter ajaxAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(ajaxAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }
}
