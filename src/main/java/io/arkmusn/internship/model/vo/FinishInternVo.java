package io.arkmusn.internship.model.vo;

/**
 * 结束实习接收VO
 *
 * @author Arkmusn
 *         create 2018/3/2
 */

public class FinishInternVo {
    private Integer id;
    private String summary;
    private Integer rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
