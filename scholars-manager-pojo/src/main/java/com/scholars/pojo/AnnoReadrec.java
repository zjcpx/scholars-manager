package com.scholars.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AnnoReadrec {
    private Long id;

    private String userno;

    private Integer goodbad;

    private Long announcementid;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public Integer getGoodbad() {
        return goodbad;
    }

    public void setGoodbad(Integer goodbad) {
        this.goodbad = goodbad;
    }

    public Long getAnnouncementid() {
        return announcementid;
    }

    public void setAnnouncementid(Long announcementid) {
        this.announcementid = announcementid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}