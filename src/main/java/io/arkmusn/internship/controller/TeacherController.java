package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.domain.entity.Teacher;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.model.vo.InternListVo;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.model.vo.TeacherListVo;
import io.arkmusn.internship.service.TeacherService;
import io.arkmusn.internship.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Controller
@RequestMapping("teacher")
public class TeacherController extends BaseController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 获取教师信息
     *
     * @param id 教师ID
     * @return 教师信息
     */
    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET)
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
    @RequestMapping(value = "/",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<Page<Teacher>> list(TeacherListVo teacherListVo) {
        Page<Teacher> list = teacherService.list(PageUtils.toPageable(teacherListVo));
        return new Response<>(list);
    }

    /**
     * 根据教师名获取教师
     *
     * @param name 教师名
     * @return 教师
     */
    @RequestMapping(value = "queryTeacherByName",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<List<Teacher>> queryTeacherByName(@RequestParam String name) {
        return new Response<>(teacherService.queryTeacherByName(name));
    }

    /**
     * 编辑教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @RequestMapping(value = "/",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Teacher teacher) {
        return new Response(teacherService.save(teacher));
    }

    /**
     * 删除教师信息
     *
     * @param ids ids
     * @return 结果
     */
    @RequestMapping(value = "delete",
                    method = RequestMethod.POST)
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
    @RequestMapping(value = "resetPassword",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response resetPassword(@RequestBody ResetPasswordVo resetPasswordVo) {
        return new Response(teacherService.resetPassword(resetPasswordVo));
    }

    /**
     * 获取教师的实习申报书列表
     *
     * @return 列表
     */
    @RequestMapping(value = "intern",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<Page<Intern>> listIntern(InternListVo internListVo) {
        return new Response<>(teacherService.listIntern(PageUtils.toPageable(internListVo)));
    }
}

