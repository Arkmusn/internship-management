package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Student;
import io.arkmusn.internship.repository.StudentInfoRepository;
import io.arkmusn.internship.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Arkmusn
 *         create 2017/11/19
 */

@Service
public class StudentInfoService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @RequiresPermissions("student:*:view")
    public Page<Student> list(Pageable page, String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return studentInfoRepository.findAll(page);
        }
        else {
            return studentInfoRepository.findAllByNoLikeOrNameLike(page, StringUtils.keywordWithSuffix(keyword), StringUtils.keywordWithSuffix(keyword));
        }
    }
}
