package com.scholars.pojo;

import java.util.Date;

public class AnnoQa {
    private Long id;

    private String questioncontent;

    private Date questiontime;

    private String questionanswers;

    private String annoname;

    private Date answertime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent == null ? null : questioncontent.trim();
    }

    public Date getQuestiontime() {
        return questiontime;
    }

    public void setQuestiontime(Date questiontime) {
        this.questiontime = questiontime;
    }

    public String getQuestionanswers() {
        return questionanswers;
    }

    public void setQuestionanswers(String questionanswers) {
        this.questionanswers = questionanswers == null ? null : questionanswers.trim();
    }

    public String getAnnoname() {
        return annoname;
    }

    public void setAnnoname(String annoname) {
        this.annoname = annoname == null ? null : annoname.trim();
    }

    public Date getAnswertime() {
        return answertime;
    }

    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }
}