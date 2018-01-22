package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Department;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Arkmusn
 *         create 2018/1/19
 */

@Controller
@RequestMapping("department")
public class DepartmentController extends BaseController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 获取系部列表
     *
     * @return 系部列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public @ResponseBody
    Response<List<Department>> list() {
        return new Response<>(departmentService.list());
    }
}
