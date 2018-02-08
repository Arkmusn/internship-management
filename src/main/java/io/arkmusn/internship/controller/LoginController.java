package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Arkmusn
 *         create 2017/11/18
 */

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
    private UserService userService;

    /**
     * 登录接口
     * 登录成功后返回该用户的角色
     *
     * @param username 用户名
     * @param request  当前请求
     * @return 该用户的角色
     */
    @RequestMapping(value = "signIn",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response signIn(@RequestParam String username, HttpServletRequest request) {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            if (IncorrectCredentialsException.class.getName().equals(exception))
                return new Response(false);
        }

        User user = userService.getUserByUsername(username);
        return new Response<>(user);
    }

    @RequestMapping(value = "signOut",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response signOut() {
        return new Response(true);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
