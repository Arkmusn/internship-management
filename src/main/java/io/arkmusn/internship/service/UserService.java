package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

@Service
public class UserService extends CrudService<User> {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 保存用户
     *
     * @param user 待保存的用户
     * @return 结果
     */
    @Override
    public User save(User user) {
        // 新建用户则保存默认密码
        if (StringUtils.isEmpty(user.getId())) {
            user.setPassword(User.INIT_PASSWORD);
        }
        return super.save(user);
    }
}
