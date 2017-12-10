package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arkmusn on 2017/11/19.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
