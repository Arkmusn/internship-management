package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn on 2017/11/19.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
