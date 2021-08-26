package com.dungtv.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dungtv.application.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, String>{
	@Transactional   
	@Query(value ="SELECT * FROM tbl_subject WHERE code = ?1", nativeQuery=true)
	Subject findSubjectByCode(String code);
	@Transactional   
	@Query(value = "SELECT * FROM tbl_subject WHERE status = 1", nativeQuery = true)
	List<Subject> getListAllSubject();
	@Transactional    
	@Query(value = "SELECT * FROM tbl_subject WHERE status = 1 AND status_register = 1", nativeQuery = true)
	List<Subject> getListAllSubjectOpen();
	@Transactional    
	@Query(value = "SELECT * FROM tbl_subject WHERE status = 1 AND status_register = 0", nativeQuery = true)
	List<Subject> getListAllSubjectClose();
	@Transactional    
	@Query(value = "SELECT * FROM tbl_subject group by id", nativeQuery = true)
	List<Subject> getListSubjects();
}
