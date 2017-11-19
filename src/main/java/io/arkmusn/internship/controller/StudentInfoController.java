package io.arkmusn.internship.controller;

import io.arkmusn.internship.model.vo.Response;
import io.arkmusn.internship.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Controller
@RequestMapping("student")
public class StudentInfoController extends BaseController {
    @Autowired
    private StudentInfoService studentInfoService;

    /**
     * 根据关键词获取学生信息列表
     *
     * @param page
     * @param keyword
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public @ResponseBody
    Response list(@PageableDefault Pageable page, @RequestParam(required = false) String keyword) {
        return new Response<>(studentInfoService.list(page, keyword));
    }
}
