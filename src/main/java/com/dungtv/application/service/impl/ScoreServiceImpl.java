package com.dungtv.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Score;
import com.dungtv.application.repository.ScoreRepository;
import com.dungtv.application.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{
	@Autowired
	private ScoreRepository scoreRepository;
	@Override
	public void addOrUpdateScore(Score score) {
		scoreRepository.save(score);
	}
	@Override
	public List<String> getListIdSubjectsRegistered(String id) {
		return scoreRepository.getListIdSubjectsRegistered(id);
	}
	@Override
	public void deleteRegisterByIdUserAndIdSubject(String accountId, String subjectId) {
		scoreRepository.deleteRegisterByIdUserAndIdSubject(accountId, subjectId);
	}
	@Override
	public void closeRegisterByIdSubject(String subjectId, String semesterId) {
		scoreRepository.closeRegisterByIdSubject(subjectId, semesterId);
	}
	@Override
	public List<Score> getListRegisteredSubjectByIdUser(String id) {
		return scoreRepository.getListRegisteredSubjectByIdUser(id);
	}
	@Override
	public List<Score> getListSemester() {
		return scoreRepository.getListSemester();
	}
	@Override
	public List<Score> getAll() {
		return scoreRepository.getAll();
	}
	@Override
	public List<Score> getListScoreByIdSubject(String subjectId) {
		return scoreRepository.getListScoreByIdSubject(subjectId);
	}
	@Override
	public List<Score> getListScoreByIdSemester(String semesterId) {
		return scoreRepository.getListScoreByIdSemester(semesterId);
	}
	@Override
	public List<Score> getListScoreByIdSemesterAndIdSubject(String semesterId, String subjectId) {
		return scoreRepository.getListScoreByIdSemesterAndIdSubject(semesterId, subjectId);
	}
	@Override
	public Score getScoreById(String id) {
		return scoreRepository.getById(id);
	}
	@Override
	public List<Score> getListSemesterByIdUser(String account_id) {
		return scoreRepository.getListSemesterByIdUser(account_id);
	}
	@Override
	public List<Score> getListScoreByIdSemesterAndAccountId(String semesrer_id, String account_id) {
		return scoreRepository.getListScoreByIdSemesterAndAccountId(semesrer_id, account_id);
	}

}
