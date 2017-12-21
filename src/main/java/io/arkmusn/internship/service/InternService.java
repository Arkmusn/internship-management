package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.repository.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
