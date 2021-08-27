package com.dungtv.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Score;

@Service
public interface ScoreService {
	public void addOrUpdateScore(Score score);
	public List<String> getListIdSubjectsRegistered(String id);
	public void deleteRegisterByIdUserAndIdSubject(String accountId, String subjectId);
	public void closeRegisterByIdSubject(String subjectId, String semesterId);
	public List<Score> getListRegisteredSubjectByIdUser(String id);
	public List<Score> getListSemester();
	public List<Score> getAll();
	public List<Score> getListScoreByIdSubject(String subjectId);
	public List<Score> getListScoreBySemesterName(String semester);
	public List<Score> getListScoreByNameSemesterAndIdSubject(String semesterName, String subjectId);
	public Score getScoreById(String id);
	public List<Score> getListSemesterByIdUser(String account_id);
	public List<Score> getListScoreByNameSemesterAndAccountId(String semesrerName, String account_id);
}