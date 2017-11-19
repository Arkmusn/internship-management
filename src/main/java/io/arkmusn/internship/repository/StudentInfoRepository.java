package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arkmusn on 2017/11/19.
 */
public interface StudentInfoRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByNoLikeOrNameLike(Pageable page, String noKeyword, String nameKeyword);
}
