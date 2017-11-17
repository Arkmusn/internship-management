package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/11/17
 */

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
