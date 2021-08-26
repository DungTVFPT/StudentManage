package com.dungtv.application.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tbl_score")
public class Score extends BaseObject {
	private String semester;
	@Column(name = "semester_id")
	private String semesterId;
	private int score;
	@Column(name = "date_register")
	private Date date;
	@Column(name = "status_register", columnDefinition = "int default 0")
	private int statusRegister;
	@OneToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	@OneToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getStatusRegister() {
		return statusRegister;
	}
	public void setStatusRegister(int statusRegister) {
		this.statusRegister = statusRegister;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
}
