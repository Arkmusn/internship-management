package io.arkmusn.internship.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 实习周报实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
@DynamicUpdate
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String time;

    private Integer position;

    @Column(columnDefinition = "TEXT",
            length = 1024)
    private String process;

    @ManyToOne
    private Intern intern;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Report() {
    }

    @Override
    public Integer getId() {
        return id.intValue();
    }

    @Override
    public void setId(Number id) {
        this.id = id.intValue();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }
}
