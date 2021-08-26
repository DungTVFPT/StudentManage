package com.dungtv.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dungtv.application.constant.SystemConstant;
import com.dungtv.application.domain.Account;
import com.dungtv.application.domain.AccountPrincipal;
import com.dungtv.application.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findAccountByUsernameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		
		if(account == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		AccountPrincipal accountPrincipal = new AccountPrincipal(account);
		return accountPrincipal;
	}

}
