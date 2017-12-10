package io.arkmusn.internship.controller;

import io.arkmusn.internship.model.bo.Response;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public @ResponseBody
    Response signIn(@RequestParam String username, HttpServletRequest request) {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            if (IncorrectCredentialsException.class.getName().equals(exception))
                return new Response(false);
        }

        return new Response();
    }
}
