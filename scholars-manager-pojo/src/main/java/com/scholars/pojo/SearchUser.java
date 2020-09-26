package com.scholars.pojo;

import java.util.Date;

public class SearchUser extends Employee {
	private Date createDateStart;
	private Date createDateEnd;
	private Date modifyDateStart;
	private Date modifyDateEnd;
	private int maxScore;
	private int minScore;
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getMinScore() {
		return minScore;
	}
	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	public Date getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public Date getModifyDateStart() {
		return modifyDateStart;
	}
	public void setModifyDateStart(Date modifyDateStart) {
		this.modifyDateStart = modifyDateStart;
	}
	public Date getModifyDateEnd() {
		return modifyDateEnd;
	}
	public void setModifyDateEnd(Date modifyDateEnd) {
		this.modifyDateEnd = modifyDateEnd;
	}
	
	
}
