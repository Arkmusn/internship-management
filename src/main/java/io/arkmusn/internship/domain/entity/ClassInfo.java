package io.arkmusn.internship.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * 班级实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
@Table(name = "CLASS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ClassInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String no;

    private String name;

    @ManyToOne
    private Department department;

    public ClassInfo() {
    }

    @Override
    public Number getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = id.intValue();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
