package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Role;
import io.arkmusn.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2018/1/20
 */

@Service
public class RoleService extends CrudService<Role> {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}
