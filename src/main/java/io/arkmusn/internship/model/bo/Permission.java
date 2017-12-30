package io.arkmusn.internship.model.bo;

import io.arkmusn.internship.domain.entity.AbstractPermission;
import io.arkmusn.internship.domain.entity.PermissionActionType;
import io.arkmusn.internship.domain.entity.PermissionEntityType;

/**
 * 权限业务类
 *
 * @author Arkmusn
 *         create 2017/11/20
 */

public class Permission extends AbstractPermission {
    public Permission(PermissionEntityType entityType, String entityId, PermissionActionType actionType) {
        this.setEntityType(entityType);
        this.setEntityId(entityId);
        this.setActionType(actionType);
    }
}
