package io.arkmusn.internship.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

/**
 * 教师实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "no", referencedColumnName = "username")
    private User user;

    private String name;

    private Boolean sex;

    @ManyToOne
    private Department department;

    public Teacher() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = id.intValue();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
