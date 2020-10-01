package com.scholars.pojo;

import java.util.Date;

public class Punishments {
    private Long id;

    private String resone;

    private Integer result;

    private String person;

    private Date createtime;

    private String describ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResone() {
        return resone;
    }

    public void setResone(String resone) {
        this.resone = resone == null ? null : resone.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ == null ? null : describ.trim();
    }
}