package io.arkmusn.internship.security.filter;

import io.arkmusn.internship.model.bo.Response;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class AjaxAuthenticationFilter extends FormAuthenticationFilter {
    private Logger log = LoggerFactory.getLogger(AjaxAuthenticationFilter.class.getName());

    /**
     * 未验证身份处理
     *
     * @param request  request
     * @param response response
     * @return 是否通过验证
     * @throws Exception null
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 当前请求正在登录则处理登录
        // 此处为shiro的登录处理
        if (isLoginRequest(request, response)) {
            return !isLoginSubmission(request, response) || executeLogin(request, response);
        }
        // 不是登录请求则返回验证未通过
        else {
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
            return false;
        }
    }
}
