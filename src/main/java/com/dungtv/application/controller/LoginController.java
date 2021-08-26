package com.dungtv.application.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dungtv.application.constant.SystemConstant;
import com.dungtv.application.domain.Account;
import com.dungtv.application.domain.AccountPrincipal;
import com.dungtv.application.domain.Subject;
import com.dungtv.application.service.AccountService;
import com.dungtv.application.service.RoleService;
import com.dungtv.application.service.SubjectService;

@Controller

public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		
		return mav;
	}
	@RequestMapping(value = "/loginSuccess")
	public ModelAndView loginSuccess() {
		LOGGER.info("Login");
		try {
			List<Subject> list = subjectService.getListAllSubjectOpen();
			Date today = new Date();
			if(list.size() > 0) {
				for (Subject subject : list) {
					if(today.after(subject.getTimeEnd())) {
						subject.setStatusRegister(SystemConstant.INACTIVE_STATUS);
						subjectService.addOrUpdateSubject(subject);
					}					
					
				}
			}
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Account account = accountService.getAccountById(accountPrincipal.getAccount().getId());
			if(account.getRoles().contains(roleService.getRoleByCode(SystemConstant.ROLE_ADMIN))) {
				return new ModelAndView("redirect:/admin");
			}
			return new ModelAndView("redirect:/");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
}
