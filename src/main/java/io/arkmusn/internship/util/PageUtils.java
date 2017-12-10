package io.arkmusn.internship.util;

import io.arkmusn.internship.domain.entity.Listable;
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
        return new PageRequest(listable.getPage(), listable.getSize());
    }
}
