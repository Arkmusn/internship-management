package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.PermissionActionType;
import io.arkmusn.internship.domain.entity.PermissionEntityType;
import io.arkmusn.internship.domain.entity.Student;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.repository.StudentRepository;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Service
public class StudentService extends CrudService<Student> {

    private StudentRepository studentRepository;
    private UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    /**
     * 重设密码
     *
     * @param resetPasswordVo 重设密码
     * @return 结果
     */
    @Transactional
    public boolean resetPassword(ResetPasswordVo resetPasswordVo) {
        Integer id = resetPasswordVo.getId();
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.STUDENT, id.toString(), PermissionActionType.RESET));
        Student student = studentRepository.findOne(id);
        Assert.notNull(student, "学生ID不存在");
        User user = student.getUser();
        user.setPassword(resetPasswordVo.getNewPassword());
        userRepository.saveAndFlush(user);
        return true;
    }
}
