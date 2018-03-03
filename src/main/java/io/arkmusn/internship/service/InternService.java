package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.*;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.model.vo.FinishInternVo;
import io.arkmusn.internship.repository.InternRepository;
import io.arkmusn.internship.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Service
public class InternService extends CrudService<Intern> {
    private PermissionService permissionService;
    private StudentService studentService;

    private InternRepository internRepository;

    @Autowired
    public InternService(PermissionService permissionService, InternRepository internRepository, StudentService studentService) {
        super(internRepository);
        this.permissionService = permissionService;
        this.internRepository = internRepository;
        this.studentService = studentService;
    }

    @Override
    public Intern save(Intern intern) {
        Student student = studentService.getCurrentStudent();
        intern.setStudent(student);
        intern.setStatus(InternStatus.CREATED);
        Intern result = super.save(intern);

        User teacherUser = result.getTeacher().getUser();
        permissionService.savePermissionForUser(new Permission(PermissionEntityType.INTERN, result.getId().toString(), PermissionActionType.UPDATE), teacherUser.getId());
        User studentUser = result.getStudent().getUser();
        permissionService.savePermissionForUser(new Permission(PermissionEntityType.INTERN, result.getId().toString(), PermissionActionType.ALL), studentUser.getId());

        return result;
    }

    /**
     * 获取学生的实习申报书列表
     * <p>教师@{@link TeacherService#list(Pageable)}</p>
     *
     * @param page 分页对象
     * @return 列表
     */
    @Override
    public Page<Intern> list(Pageable page) {
        Student student = studentService.getCurrentStudent();
        Intern intern = new Intern();
        intern.setStudent(student);
        return internRepository.findAll(Example.of(intern), page);
    }

    /**
     * 申报书审核通过
     *
     * @param ids 申报书ID列表
     * @return 通过数量
     */
    @Transactional
    public int audit(Collection<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            PermissionUtils.checkPermission(new Permission(PermissionEntityType.INTERN, id.toString(), PermissionActionType.UPDATE));
            Intern intern = internRepository.findOne(id);
            intern.setStatus(InternStatus.PROCESSING);
            internRepository.save(intern);
            count++;
        }
        return count;
    }

    /**
     * 申报书审核打回
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    public int reject(Collection<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            PermissionUtils.checkPermission(new Permission(PermissionEntityType.INTERN, id.toString(), PermissionActionType.UPDATE));
            Intern intern = internRepository.findOne(id);
            intern.setStatus(InternStatus.NOT_PASS);
            internRepository.save(intern);
            count++;
        }
        return count;
    }

    /**
     * 结束实习
     *
     * @param finishInternVo {@link FinishInternVo}
     * @return 结果
     */
    public boolean finish(FinishInternVo finishInternVo) {
        Integer id = finishInternVo.getId();
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.INTERN, id.toString(), PermissionActionType.UPDATE));
        Intern intern = internRepository.findOne(id);
        intern.setStatus(InternStatus.FINISHED);
        intern.setSummary(finishInternVo.getSummary());
        return internRepository.save(intern) != null;
    }

    /**
     * 实习评分
     *
     * @param finishInternVo {@link FinishInternVo}
     * @return 结果
     */
    public boolean rank(FinishInternVo finishInternVo) {
        Integer id = finishInternVo.getId();
        PermissionUtils.checkPermission(new Permission(PermissionEntityType.INTERN, id.toString(), PermissionActionType.UPDATE));
        Intern intern = internRepository.findOne(id);
        intern.setStatus(InternStatus.END);
        intern.setRank(finishInternVo.getRank());
        return internRepository.save(intern) != null;
    }
}
