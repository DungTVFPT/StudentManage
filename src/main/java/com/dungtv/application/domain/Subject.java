package com.dungtv.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_subject")
public class Subject extends BaseObject{
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "hours", columnDefinition = "int default 1")
	private int hours;
	@Column(name = "time_start")
	private Date timeStart;
	@Column(name = "time_end")
	private Date timeEnd;
	@Column(name = "number", columnDefinition = "int default 0")
	private int number;
	@Column(name = "number_registered", columnDefinition = "int default 0")
	private int numberRegistered;
	@Column(name = "status")
	private int status;
	@Column(name = "status_register")
	private int statusRegister;
	@Column(name = "semester")
	private String semester;
	@Column(name = "semesterId")
	private String semesterId;
	public Subject() {
	}
	
	public Subject(String id, String code, String name, int hours, int number) {
		super();
		this.code = code;
		this.name = name;
		this.hours = hours;
		this.number = number;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public Date getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNumberRegistered() {
		return numberRegistered;
	}

	public void setNumberRegistered(int numberRegistered) {
		this.numberRegistered = numberRegistered;
	}
	public int getStatusRegister() {
		return statusRegister;
	}

	public void setStatusRegister(int statusRegister) {
		this.statusRegister = statusRegister;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
}
