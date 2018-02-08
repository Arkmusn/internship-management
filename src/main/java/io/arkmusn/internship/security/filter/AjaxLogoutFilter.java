package io.arkmusn.internship.security.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义登出过滤器
 *
 * @author Arkmusn
 *         create 2018/2/8
 */

public class AjaxLogoutFilter extends LogoutFilter {
    private Logger log = LoggerFactory.getLogger(AjaxLogoutFilter.class);

    /**
     * 登出处理
     *
     * @param request  request
     * @param response response
     * @return 登出后是否继续传入过滤器链
     * @throws Exception null
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        return true;
    }
}
