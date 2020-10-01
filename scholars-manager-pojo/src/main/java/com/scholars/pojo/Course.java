package com.scholars.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Course {
    private Long id;

    private String coursename;

    private String studyperson;

    private String scores;

    private String memo;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifytime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dateline;

    private String author;

    private String coursecontents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getStudyperson() {
        return studyperson;
    }

    public void setStudyperson(String studyperson) {
        this.studyperson = studyperson == null ? null : studyperson.trim();
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores == null ? null : scores.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCoursecontents() {
        return coursecontents;
    }

    public void setCoursecontents(String coursecontents) {
        this.coursecontents = coursecontents == null ? null : coursecontents.trim();
    }
}