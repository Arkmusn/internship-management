package io.arkmusn.internship.model.vo;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.model.bo.Listable;

/**
 * 实习申报书列表参数接收对象
 *
 * @author Arkmusn
 *         create 2017/12/21
 */

public class InternListVo extends Intern implements Listable {
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
