package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.PermissionActionType;
import io.arkmusn.internship.domain.entity.PermissionEntityType;
import io.arkmusn.internship.domain.entity.Teacher;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.repository.TeacherRepository;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import io.arkmusn.internship.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取教师信息
     *
     * @param id 教师ID
     * @return 教师信息
     */
    public Teacher get(Integer id) {
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.TEACHER, id.toString(), PermissionActionType.VIEW));
        return teacherRepository.findOne(id);
    }

    /**
     * 获取教师信息列表
     *
     * @param page 分页对象
     * @return 分页教师信息
     */
    @RequiresPermissions("teacher:list")
    public Page<Teacher> list(Pageable page) {
        return teacherRepository.findAll(page);
    }

    /**
     * 编辑教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @Transactional
    public boolean edit(Teacher teacher) {
        // 新增
        Integer id = teacher.getId();
        if (StringUtils.isEmpty(id)) {
            PermissionUtils.checkPermission("teacher:create");
        }
        // 编辑
        else {
            PermissionUtils.checkPermission(new Permission(PermissionEntityType.TEACHER, String.valueOf(id), PermissionActionType.UPDATE));
        }
        return true;
    }

    /**
     * 删除教师信息
     *
     * @param ids ids
     * @return count
     */
    @Transactional
    public int delete(Collection<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            PermissionUtils.checkPermission(new Permission(PermissionEntityType.TEACHER, id.toString(), PermissionActionType.DELETE));
            teacherRepository.delete(id);
            count++;
        }
        return count;
    }

    /**
     * 重设密码
     *
     * @param resetPasswordVo 重设密码VO
     * @return 结果
     */
    @Transactional
    public boolean resetPassword(ResetPasswordVo resetPasswordVo) {
        Integer id = resetPasswordVo.getId();
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.TEACHER, id.toString(), PermissionActionType.RESET));
        Teacher teacher = teacherRepository.findOne(id);
        Assert.notNull(teacher, "教师ID不存在");
        User user = teacher.getUser();
        user.setPassword(resetPasswordVo.getNewPassword());
        userRepository.saveAndFlush(user);
        return true;
    }
}
