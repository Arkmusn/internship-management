package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.ClassInfo;
import io.arkmusn.internship.repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arkmusn
 *         create 2018/1/24
 */

@Service
public class ClassInfoService extends CrudService {
    private ClassInfoRepository classInfoRepository;

    @Autowired
    public ClassInfoService(ClassInfoRepository classInfoRepository) {
        super(classInfoRepository);
        this.classInfoRepository = classInfoRepository;
    }

    /**
     * 获取班级列表
     *
     * @return 班级列表
     */
    public List<ClassInfo> list() {
        return classInfoRepository.findAll();
    }
}
