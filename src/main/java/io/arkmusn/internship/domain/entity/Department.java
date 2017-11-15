package io.arkmusn.internship.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 系部实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String no;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ClassInfo> classInfos;

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }
}
