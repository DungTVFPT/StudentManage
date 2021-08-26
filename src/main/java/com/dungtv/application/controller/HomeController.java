package com.dungtv.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dungtv.application.service.SubjectService;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		LOGGER.info("Home page");
		try {
			ModelAndView mav = new ModelAndView("user/index");
			mav.addObject("listSubjects", subjectService.getListAllSubject());
			// Hello, <label
			// th:text="${#authentication.getPrincipal().getFullname()}">Guest</label>
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = { "/admin" })
	public ModelAndView homeAdmin() {
		LOGGER.info("Admin home page");
		try {
			ModelAndView mav = new ModelAndView("admin/index");
			mav.addObject("listSubjects", subjectService.getListAllSubject());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = { "/403" })
	public ModelAndView accessDenied() {
		LOGGER.info("403 page");
		try {
			ModelAndView mav = new ModelAndView("403");
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

}
