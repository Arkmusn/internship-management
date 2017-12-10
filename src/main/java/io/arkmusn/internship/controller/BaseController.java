package io.arkmusn.internship.controller;

import io.arkmusn.internship.model.bo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Arkmusn
 *         create 2017/11/18
 */

@ControllerAdvice
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public @ResponseBody
    Response handleException(Exception e) {
        return new Response<>(false, Response.CODE_FAIL, e.getMessage());
    }
}
