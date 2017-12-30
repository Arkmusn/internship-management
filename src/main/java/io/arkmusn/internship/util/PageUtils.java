package io.arkmusn.internship.util;

import io.arkmusn.internship.model.bo.Listable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 分页工具类
 *
 * @author Arkmusn
 *         create 2017/12/10
 */

abstract public class PageUtils {
    public static Pageable toPageable(Listable listable) {
        int page = listable.getPage();
        int size = listable.getSize();
        return new PageRequest(page == 0 ? 1 : page, size == 0 ? 10 : size);
    }
}
