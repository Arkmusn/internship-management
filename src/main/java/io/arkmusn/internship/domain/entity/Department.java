package io.arkmusn.internship.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * 系部实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String no;

    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<ClassInfo> classInfos;

    public Department() {
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

    public Set<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(Set<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }
}
