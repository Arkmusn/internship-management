package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.*;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.repository.RoleRepository;
import io.arkmusn.internship.repository.StudentRepository;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import io.arkmusn.internship.util.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;

/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Service
public class StudentService extends CrudService<Student> {
    private UserService userService;

    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public StudentService(UserService userService, StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        super(studentRepository);
        this.userService = userService;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        user.setPassword(new Sha256Hash(resetPasswordVo.getNewPassword()).toHex());
        userRepository.saveAndFlush(user);
        return true;
    }

    /**
     * 保存学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    @Override
    @Transactional
    public boolean save(Student student) {
        User user = student.getUser();
        // 新建学生用户
        if (StringUtils.isEmpty(user.getId())) {
            HashSet<Role> roles = new HashSet<>(1, 1F);
            roles.add(roleRepository.findByName("student"));
            user.setRoles(roles);
        }
        userService.save(user);
        return super.save(student);
    }

    /**
     * 获取当前登录的学生信息
     *
     * @return 学生信息
     */
    public Student getCurrentStudent() {
        Student student = new Student();
        student.setUser(getCurrentUser());
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("user.id", match -> match.exact());
        Example<Student> example = Example.of(student, matcher);
        student = studentRepository.findOne(example);
        Assert.notNull(student, "No student at this session!");
        return student;
    }
}
