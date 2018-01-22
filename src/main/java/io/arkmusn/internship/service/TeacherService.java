package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.*;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.model.vo.ResetPasswordVo;
import io.arkmusn.internship.repository.RoleRepository;
import io.arkmusn.internship.repository.TeacherRepository;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import io.arkmusn.internship.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Service
public class TeacherService extends CrudService<Teacher> {
    private UserService userService;

    private TeacherRepository teacherRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public TeacherService(UserService userService, TeacherRepository teacherRepository, UserRepository userRepository, RoleRepository roleRepository) {
        super(teacherRepository);
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    /**
     * 保存教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @Override
    @Transactional
    public boolean save(Teacher teacher) {
        User user = teacher.getUser();
        // 新建教师用户
        if (StringUtils.isEmpty(user.getId())) {
            HashSet<Role> roles = new HashSet<>(1, 1F);
            roles.add(roleRepository.findByName("teacher"));
            user.setRoles(roles);
        }
        userService.save(user);
        return super.save(teacher);
    }
}
