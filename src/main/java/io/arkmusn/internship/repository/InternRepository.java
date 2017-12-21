package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn on 2017/12/11.
 */
@Repository
public interface InternRepository extends JpaRepository<Intern, Integer> {
}
