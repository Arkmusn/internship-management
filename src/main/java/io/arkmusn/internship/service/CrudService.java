package io.arkmusn.internship.service;

import io.arkmusn.internship.domain.entity.BaseEntity;
import io.arkmusn.internship.domain.entity.PermissionActionType;
import io.arkmusn.internship.domain.entity.User;
import io.arkmusn.internship.model.bo.Permission;
import io.arkmusn.internship.repository.UserRepository;
import io.arkmusn.internship.util.PermissionUtils;
import io.arkmusn.internship.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * 通用service
 *
 * @author Arkmusn
 *         create 2017/12/12
 */

abstract public class CrudService<T extends BaseEntity> {

    protected JpaRepository<T, Number> repository;
    private UserRepository userRepository;

    private Class<T> entityClass;
    private String className;

    public CrudService(JpaRepository repository) {
        this.repository = repository;
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = ClassUtils.getShortName(entityClass);
    }

    /**
     * 获取实体信息
     *
     * @param id 实体ID
     * @return 实体信息
     */
    @SuppressWarnings("unchecked")
    public T get(Number id) {
        PermissionUtils.checkPermission(className.toLowerCase(), id.toString());
        return (T) repository.findOne(id);
    }

    /**
     * 编辑实体信息
     *
     * @param entity 实体信息
     * @return 结果
     */
    @Transactional
    public T save(T entity) {
        // 新增
        Number id = entity.getId();
        if (StringUtils.isEmpty(id) || id.equals(-1)) {
            PermissionUtils.checkPermission(className.toLowerCase(), Permission.UNKNOWN_ENTITY_ID, PermissionActionType.CREATE.toString().toLowerCase());
        }
        // 编辑
        else {
            PermissionUtils.checkPermission(className.toLowerCase(), id.toString(), PermissionActionType.UPDATE.toString().toLowerCase());
        }
        return repository.save(entity);
    }

    /**
     * 删除实体信息
     *
     * @param ids 实体id列表
     * @return 删除数量
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public int delete(Collection<? extends Number> ids) {
        int count = 0;
        for (Number id : ids) {
            PermissionUtils.checkPermission(className.toLowerCase(), id.toString(), PermissionActionType.DELETE.toString().toLowerCase());
            repository.delete(id);
            count++;
        }
        return count;
    }

    /**
     * 获取实体信息列表
     *
     * @param page 分页对象
     * @return 分页实体信息
     */
    @SuppressWarnings("unchecked")
    public Page<T> list(Pageable page) {
        PermissionUtils.checkPermission(className.toLowerCase(), Permission.UNKNOWN_ENTITY_ID, PermissionActionType.LIST.toString().toLowerCase());
        return repository.findAll(page);
    }

    public User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Assert.notNull(subject, "No subject at this session!");
        return userRepository.findByUsername(subject.getPrincipal().toString());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
