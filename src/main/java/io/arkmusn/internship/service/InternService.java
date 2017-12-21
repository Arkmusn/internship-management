package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.repository.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Service
public class InternService extends CrudService<Intern> {

    private InternRepository internRepository;

    @Autowired
    public InternService(InternRepository internRepository) {
        super(internRepository);
        this.internRepository = internRepository;
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
