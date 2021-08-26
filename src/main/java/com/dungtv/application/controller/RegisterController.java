package com.dungtv.application.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dungtv.application.constant.SystemConstant;
import com.dungtv.application.domain.Account;
import com.dungtv.application.domain.Role;
import com.dungtv.application.service.AccountService;
import com.dungtv.application.service.RoleService;

@Controller

public class RegisterController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");

		return mav;
	}
	@RequestMapping(value = "/registerOrUpdateAccount")
	public ModelAndView registerAccount(@RequestParam(name = "id") String id, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "fullname") String fullname,
			@RequestParam(name = "dob") String dob, @RequestParam(name = "gender") String gender,
			@RequestParam("address") String address, RedirectAttributes redirAttrs){
		LOGGER.info("Register or update account");
		try {
			Account account = accountService.getAccountByUsername(username);
			if(id.trim().isEmpty()) {
				// Check user name exist
				if (account != null) {
					redirAttrs.addFlashAttribute("accountExist", true);
					return new ModelAndView("redirect:/register");
				}
				// Set information only new account need
				account = new Account();
				account.setUsername(username);
				String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
				account.setPassword(hashpw);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
					account.setDob(date);
				} catch (Exception e) {

				}
				if (!gender.equalsIgnoreCase("male")) {
					account.setGender(false);
				} else {
					account.setGender(true);
				}
				account.setAddress(address);
				List<Role> roles = new ArrayList<Role>();
				roles.add(roleService.getRoleByCode(SystemConstant.ROLE_USER));
				account.setRoles(roles);
				account.setStatus(1);
				account.setFullName(fullname);

				accountService.addOrUpdateStudent(account);
				redirAttrs.addFlashAttribute("createSuccess", true);
				return new ModelAndView("redirect:/register");
			}else {
				// If id not null then update
				account.setFullName(fullname);

				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
					account.setDob(date);
				} catch (Exception e) {

				}
				if (!gender.equalsIgnoreCase("male")) {
					account.setGender(false);
				} else {
					account.setGender(true);
				}
				account.setAddress(address);

				accountService.addOrUpdateStudent(account);
				ModelAndView mav = new ModelAndView("redirect:/updateProfile");
				mav.addObject("updateSuccess", true);
				return mav;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
		

	}
}
