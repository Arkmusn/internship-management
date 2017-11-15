package io.arkmusn.internship.domain.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 实习实体类
 *
 * @author Arkmusn
 *         create 2017/11/14
 */

@Entity
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Teacher teacher;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date startTime;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date endTime;

    private String companyName;

    private String companyAddress;

    @Column(columnDefinition = "TEXT", length = 4096)
    private String object;

    @Column(columnDefinition = "TEXT", length = 1024)
    private String arrangement;

    private Integer weekday;

    private Integer rank;

    private Boolean attachment;

    @OneToMany(mappedBy = "id")
    private List<Report> reports;

    public Intern() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
