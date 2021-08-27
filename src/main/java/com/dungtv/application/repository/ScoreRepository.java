package com.dungtv.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dungtv.application.domain.Score;
@Repository
public interface ScoreRepository extends JpaRepository<Score, String>{
	@Transactional   
	@Query(value = "SELECT tbl_score.subject_id FROM tbl_score WHERE account_id = ?1 and status_register = 1", nativeQuery = true)
	List<String> getListIdSubjectsRegistered(String account_id);
	@Transactional   
	@Modifying
	@Query(value = "DELETE FROM tbl_score WHERE account_id = ?1 and subject_id = ?2 and status_register = 1", nativeQuery = true)
	void deleteRegisterByIdUserAndIdSubject(String account_id, String subject_id);
	
	@Transactional   
	@Modifying
	@Query(value = "UPDATE tbl_score SET status_register = 0 WHERE subject_id = ?1 and semester_id = ?2", nativeQuery = true)
	void closeRegisterByIdSubject(String subject_id, String semester_id);
	@Transactional    
	@Query(value = "SELECT * FROM tbl_score WHERE account_id = ?1 and status_register = 0", nativeQuery = true)
	List<Score> getListRegisteredSubjectByIdUser(String account_id);
	@Transactional    
	@Query(value = "SELECT * FROM tbl_score group by semester", nativeQuery = true)
	List<Score> getListSemester();
	@Transactional    
	@Query(value = "SELECT * FROM tbl_score WHERE status_register = 0 order by semester", nativeQuery = true)
	List<Score> getAll();
	@Transactional    
	@Query(value = "SELECT * FROM tbl_score WHERE subject_id = ?1 and status_register = 0 order by semester", nativeQuery = true)
	List<Score> getListScoreByIdSubject(String subject_id);
	@Transactional 
	@Query(value = "SELECT * FROM tbl_score WHERE semester = ?1 and status_register = 0", nativeQuery = true)
	List<Score> getListScoreBySemesterName(String semester);
	@Transactional 
	@Query(value = "SELECT * FROM tbl_score WHERE semester = ?1 and subject_id = ?2 and status_register = 0 order by semester", nativeQuery = true)
	List<Score> getListScoreByNameSemesterAndIdSubject(String semester, String subject_id);
	@Transactional 
	@Query(value = "SELECT * FROM tbl_score WHERE account_id = ?1 and status_register = 0 group by semester", nativeQuery = true)
	List<Score> getListSemesterByIdUser(String account_id);
	@Query(value = "SELECT * FROM tbl_score WHERE semester = ?1 and account_id = ?2 and status_register = 0 order by semester", nativeQuery = true)
	List<Score> getListScoreByNameSemesterAndAccountId(String semester, String account_id);
}
