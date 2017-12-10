package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.PermissionActionType;
import io.arkmusn.internship.domain.entity.PermissionEntityType;
import io.arkmusn.internship.domain.entity.Student;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.repository.StudentRepository;
import io.arkmusn.internship.util.PermissionUtils;
import io.arkmusn.internship.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 获取学生信息
     *
     * @param id 学生ID
     * @return 学生信息
     */
    public Student get(Integer id) {
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.STUDENT, id.toString(), PermissionActionType.VIEW));
        return studentRepository.findOne(id);
    }

    /**
     * 获取学生信息列表
     *
     * @param page 分页对象
     * @return 分页学生信息
     */
    @RequiresPermissions("student:list")
    public Page<Student> list(Pageable page) {
        return studentRepository.findAll(page);
    }

    /**
     * 编辑学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    public boolean edit(Student student) {
        // 新增
        Integer id = student.getId();
        if (StringUtils.isEmpty(id)) {
            PermissionUtils.checkPermission("student:add");
        }
        // 编辑
        else {
            PermissionUtils.checkPermission(new Permission(PermissionEntityType.STUDENT, String.valueOf(id), PermissionActionType.UPDATE));
        }
        return true;
    }
}
