package com.dungtv.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Account;

@Service
public interface AccountService {
	public void addOrUpdateStudent(Account account);
	public Account getAccountById(String id);
	public Account getAccountByUsername(String Username);
	public List<Account> getAccountStudentActive();
}
