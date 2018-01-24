package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn on 2018/1/24.
 */
@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Integer> {

}
