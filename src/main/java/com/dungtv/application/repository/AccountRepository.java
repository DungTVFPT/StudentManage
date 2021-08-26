package com.dungtv.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dungtv.application.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	@Transactional    
	@Query(value ="SELECT * FROM tbl_account WHERE username = ?1 AND status = ?2", nativeQuery=true)
	Account findAccountByUsernameAndStatus(String username, int status);
	@Transactional   
	@Query(value ="SELECT * FROM tbl_account WHERE username = ?1", nativeQuery=true)
	Account findAccountByUsername(String username);
	@Transactional   
	@Query(value ="select * from tbl_account\r\n"
			+ "left join tbl_user_role\r\n"
			+ "on tbl_account.id = tbl_user_role.user_id\r\n"
			+ "left join tbl_role on tbl_user_role.role_id = tbl_role.id\r\n"
			+ "where tbl_role.code = \"USER\" and tbl_account.status = 1;", nativeQuery=true)
	List<Account> getAccountStudentActive();
}
