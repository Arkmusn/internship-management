package io.arkmusn.internship.domain.entity;

import java.io.Serializable;

/**
 * 实体基类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

abstract public class BaseEntity implements Serializable {
    protected Number id;

    public abstract Number getId();

    public abstract void setId(Number id);
}
