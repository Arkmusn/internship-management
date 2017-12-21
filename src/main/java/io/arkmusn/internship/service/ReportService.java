package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Report;
import io.arkmusn.internship.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/12/21
 */

@Service
public class ReportService extends CrudService<Report> {

    private ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        super(reportRepository);
        this.reportRepository = reportRepository;
    }
}
