package com.scholars.pojo;

import java.util.Date;

public class AnnoCommonts {
    private Long id;

    private String announcename;

    private String commuser;

    private String commtype;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnouncename() {
        return announcename;
    }

    public void setAnnouncename(String announcename) {
        this.announcename = announcename == null ? null : announcename.trim();
    }

    public String getCommuser() {
        return commuser;
    }

    public void setCommuser(String commuser) {
        this.commuser = commuser == null ? null : commuser.trim();
    }

    public String getCommtype() {
        return commtype;
    }

    public void setCommtype(String commtype) {
        this.commtype = commtype == null ? null : commtype.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}