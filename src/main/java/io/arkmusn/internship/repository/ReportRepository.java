package io.arkmusn.internship.repository;

import io.arkmusn.internship.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arkmusn on 2017/12/21.
 */

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

}
