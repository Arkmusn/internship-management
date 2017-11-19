package io.arkmusn.internship.domain.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/15
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractPermission extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private PermissionEntityType entityType;

    private String entityId;

    @Enumerated(EnumType.STRING)
    private PermissionActionType actionType;

    public AbstractPermission() {
    }

    public PermissionEntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(PermissionEntityType entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public PermissionActionType getActionType() {
        return actionType;
    }

    public void setActionType(PermissionActionType actionType) {
        this.actionType = actionType;
    }
}
