package com.dungtv.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dungtv.application.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	@Transactional   
	@Query(value ="SELECT * FROM tbl_role WHERE code = ?1", nativeQuery=true)
	Role getRoleByCode(String code);
}
