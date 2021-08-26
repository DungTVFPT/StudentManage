package com.dungtv.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dungtv.application.constant.SystemConstant;
import com.dungtv.application.domain.Account;
import com.dungtv.application.domain.AccountPrincipal;
import com.dungtv.application.service.AccountService;

@Controller
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = {"/updateProfile"})
	public ModelAndView updateProfile() {
		LOGGER.info("Update profile");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Account account = accountService.getAccountById(accountPrincipal.getAccount().getId());
			
			ModelAndView mav = new ModelAndView("updateprofile");
			mav.addObject("account", account);
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}	
	}
	
	@RequestMapping(value = {"/admin/manage-student"})
	public ModelAndView manageStudent() {
		LOGGER.info("Manage student");
		try {
			ModelAndView mav = new ModelAndView("admin/manageStudent");
			mav.addObject("listStudent", accountService.getAccountStudentActive());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = {"/admin/manage-student/lock-account"})
	public String lockAccount(@RequestParam(name = "id") String id) {	
		LOGGER.info("Manage student lock account");
		try {
			Account account = accountService.getAccountById(id);
			account.setStatus(SystemConstant.INACTIVE_STATUS);
			accountService.addOrUpdateStudent(account);
			
			return "redirect:/admin/manage-student?alertLock=true";
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
}
