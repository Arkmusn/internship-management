package io.arkmusn.internship.model.vo;

import io.arkmusn.internship.domain.entity.Teacher;
import io.arkmusn.internship.model.bo.Listable;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

public class TeacherListVo extends Teacher implements Listable {
    private int page;
    private int size;

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
