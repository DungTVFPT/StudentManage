package com.dungtv.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Subject;

@Service
public interface SubjectService {
	public void addOrUpdateSubject(Subject subject);
	public Subject getSubjectByCode(String code);
	public Subject getSubjectById(String id);
	public List<Subject> getListAllSubject();
	public List<Subject> getListAllSubjectOpen();
	public List<Subject> getListAllSubjectClose();
	public List<Subject> getListSubjects();
}
