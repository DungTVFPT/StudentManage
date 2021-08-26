package com.dungtv.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtv.application.domain.Account;
import com.dungtv.application.repository.AccountRepository;
import com.dungtv.application.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public void addOrUpdateStudent(Account account) {
		accountRepository.save(account);
	}
	@Override
	public Account getAccountById(String id) {
		return accountRepository.getById(id);
	}
	@Override
	public Account getAccountByUsername(String Username) {		
		return accountRepository.findAccountByUsername(Username);
	}
	@Override
	public List<Account> getAccountStudentActive() {
		return accountRepository.getAccountStudentActive();
	}

}
