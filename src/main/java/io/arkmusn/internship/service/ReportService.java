package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.*;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/12/21
 */

@Service
public class ReportService extends CrudService<Report> {

    private PermissionService permissionService;

    private ReportRepository reportRepository;

    @Autowired
    public ReportService(PermissionService permissionService, ReportRepository reportRepository) {
        super(reportRepository);
        this.permissionService = permissionService;
        this.reportRepository = reportRepository;
    }

    @Override
    public Report save(Report entity) {
        Report result = super.save(entity);
        Student student = result.getIntern().getStudent();
        permissionService.savePermissionForUser(new Permission(PermissionEntityType.REPORT, result.getId().toString(), PermissionActionType.VIEW), student.getUser().getId());
        permissionService.savePermissionForUser(new Permission(PermissionEntityType.REPORT, result.getId().toString(), PermissionActionType.UPDATE), student.getUser().getId());

        Teacher teacher = result.getIntern().getTeacher();
        permissionService.savePermissionForUser(new Permission(PermissionEntityType.REPORT, result.getId().toString(), PermissionActionType.VIEW), teacher.getUser().getId());
        return result;
    }
}
