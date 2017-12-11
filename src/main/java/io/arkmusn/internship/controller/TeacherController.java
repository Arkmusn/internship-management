package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Teacher;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.model.vo.TeacherListVo;
import io.arkmusn.internship.service.TeacherService;
import io.arkmusn.internship.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Controller
@RequestMapping("teacher")
public class TeacherController extends BaseController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取教师信息
     *
     * @param id 教师ID
     * @return 教师信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response<Teacher> get(@PathVariable Integer id) {
        return new Response<>(teacherService.get(id));
    }

    /**
     * 获取教师信息列表
     *
     * @param teacherListVo 教师信息分页对象
     * @return 分页教师信息
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Response<Page<Teacher>> list(@RequestBody TeacherListVo teacherListVo) {
        return new Response<>(teacherService.list(PageUtils.toPageable(teacherListVo)));
    }

    /**
     * 编辑教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Teacher teacher) {
        return new Response(teacherService.edit(teacher));
    }

    /**
     * 删除教师信息
     *
     * @param ids ids
     * @return 结果
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody
    Response<Integer> delete(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, teacherService.delete(ids));
    }

    /**
     * 重设密码
     *
     * @param resetPasswordVo 重设密码VO
     * @return 结果
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public @ResponseBody
    Response resetPassword(@RequestBody ResetPasswordVo resetPasswordVo) {
        return new Response(teacherService.resetPassword(resetPasswordVo));
    }
}
