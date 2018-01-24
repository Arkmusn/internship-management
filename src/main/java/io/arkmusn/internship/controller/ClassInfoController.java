package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.ClassInfo;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Arkmusn
 *         create 2018/1/24
 */

@Controller
@RequestMapping("classInfo")
public class ClassInfoController {
    private ClassInfoService classInfoService;

    @Autowired
    public ClassInfoController(ClassInfoService classInfoService) {
        this.classInfoService = classInfoService;
    }

    /**
     * 获取班级列表
     *
     * @return 班级列表
     */
    @RequestMapping(value = "list",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<List<ClassInfo>> list() {
        return new Response<>(classInfoService.list());
    }
}
