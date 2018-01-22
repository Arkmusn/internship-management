package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Student;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.model.vo.StudentListVo;
import io.arkmusn.internship.service.StudentService;
import io.arkmusn.internship.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Controller
@RequestMapping("student")
public class StudentController extends BaseController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 获取学生信息
     *
     * @param id 学生ID
     * @return 学生信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response<Student> get(@PathVariable Integer id) {
        return new Response<>(studentService.get(id));
    }

    /**
     * 获取学生信息列表
     *
     * @param studentListVo 学生信息分页对象
     * @return 分页学生信息
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Response<Page<Student>> list(@RequestBody StudentListVo studentListVo) {
        return new Response<>(studentService.list(PageUtils.toPageable(studentListVo)));
    }

    /**
     * 编辑学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Student student) {
        return new Response(studentService.save(student));
    }

    /**
     * 删除学生信息
     *
     * @param ids ids
     * @return 结果
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody
    Response<Integer> delete(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, studentService.delete(ids));
    }

    /**
     * 重设密码
     *
     * @param resetPasswordVo 重设密码
     * @return 结果
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public @ResponseBody
    Response resetPassword(@RequestBody ResetPasswordVo resetPasswordVo) {
        return new Response(studentService.resetPassword(resetPasswordVo));
    }
}
