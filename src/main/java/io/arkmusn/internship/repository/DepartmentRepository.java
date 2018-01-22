package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn
 *         create 2018/1/19
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
