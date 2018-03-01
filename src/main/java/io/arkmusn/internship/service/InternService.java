package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.*;
import io.arkmusn.internship.model.bo.Permission;
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
    private StudentService studentService;

    private InternRepository internRepository;

    @Autowired
    public InternService(InternRepository internRepository, StudentService studentService) {
        super(internRepository);
        this.internRepository = internRepository;
        this.studentService = studentService;
    }

    @Override
    public boolean save(Intern intern) {
        Student student = studentService.getCurrentStudent();
        intern.setStudent(student);
        intern.setStatus(InternStatus.CREATED);
        return super.save(intern);
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
     * 结束实习
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    public int finish(Collection<Integer> ids) {
        // TODO 引入工作流后补全
        return -1;
    }
}
