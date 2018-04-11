package io.arkmusn.internship.domain.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Arkmusn
 *         create 2018/3/14
 */

@Entity
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(columnDefinition = "TEXT",
            length = 1024)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date updateDate;

    @Enumerated(EnumType.STRING)
    private AnnouncementType announcementType;

    public Announcement() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    @Override
    public Number getId() {
        return null;
    }

    @Override
    public void setId(Number id) {
    }
}
