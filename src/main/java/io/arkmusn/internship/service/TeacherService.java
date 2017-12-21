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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Service
public class TeacherService extends CrudService<Teacher> {

    private TeacherRepository teacherRepository;
    private UserRepository userRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
        super(teacherRepository);
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
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
