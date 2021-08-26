package com.dungtv.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Subject;
import com.dungtv.application.repository.SubjectRepository;
import com.dungtv.application.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectRepository subjectRepository;
	@Override
	public void addOrUpdateSubject(Subject subject) {
		subjectRepository.save(subject);
	}

	@Override
	public Subject getSubjectByCode(String code) {
		return subjectRepository.findSubjectByCode(code);
	}

	@Override
	public Subject getSubjectById(String id) {
		return subjectRepository.getById(id);
	}

	@Override
	public List<Subject> getListAllSubject() {
		return subjectRepository.getListAllSubject();
	}

	@Override
	public List<Subject> getListAllSubjectOpen() {
		return subjectRepository.getListAllSubjectOpen();
	}

	@Override
	public List<Subject> getListAllSubjectClose() {
		return subjectRepository.getListAllSubjectClose();
	}

	@Override
	public List<Subject> getListSubjects() {
		return subjectRepository.getListSubjects();
	}

}
