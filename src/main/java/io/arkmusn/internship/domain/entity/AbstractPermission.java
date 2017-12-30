package io.arkmusn.internship.domain.entity;

import javax.persistence.*;

/**
 * 权限实体类
 *
 * @author Arkmusn
 *         create 2017/11/15
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractPermission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private PermissionEntityType entityType;

    private String entityId;

    @Enumerated(EnumType.STRING)
    private PermissionActionType actionType;

    public AbstractPermission() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = id.longValue();
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
