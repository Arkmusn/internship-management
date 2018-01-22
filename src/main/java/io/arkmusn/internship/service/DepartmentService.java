package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Department;
import io.arkmusn.internship.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arkmusn
 *         create 2018/1/19
 */

@Service
public class DepartmentService extends CrudService<Department> {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
    }

    /**
     * 获取系部列表
     *
     * @return 系部列表
     */
    public List<Department> list() {
        return departmentRepository.findAll();
    }
}
