package io.arkmusn.internship.security.filter;

import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录验证过滤器
 *
 * @author Arkmusn
 *         create 2018/1/21
 */

@Component
public class AjaxAuthenticationFilter extends FormAuthenticationFilter {
    private Logger log = LoggerFactory.getLogger(AjaxAuthenticationFilter.class.getName());

    private UserService userService;

    /**
     * 未验证身份处理
     *
     * @param request  request
     * @param response response
     * @return 是否需要继续在过滤器链传递
     * @throws Exception null
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 当前请求正在登录则处理登录
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                // 此处为shiro的登录处理
                executeLogin(request, response);
            }
            // 返回true表示继续在过滤器链中传递，交由controller继续处理
            return true;
        }
        // 不是登录请求则返回验证未通过
        else {
            sendAccessDeniedAjaxResponse(response);
            return false;
        }
    }

    private void sendAccessDeniedAjaxResponse(ServletResponse response) {
        JSONObject responseJson = new JSONObject(new Response<>(false, Response.CODE_UNAUTHENTICATED));

        PrintWriter out = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            responseJson.remove("data");
            out = response.getWriter();
            out.println(responseJson);
            out.flush();
        } catch (IOException e) {
            log.error(e.toString());
        } finally {
            if (out != null)
                out.close();
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
