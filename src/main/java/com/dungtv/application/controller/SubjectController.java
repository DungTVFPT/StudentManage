package com.dungtv.application.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dungtv.application.constant.SystemConstant;
import com.dungtv.application.domain.AccountPrincipal;
import com.dungtv.application.domain.Score;
import com.dungtv.application.domain.Subject;
import com.dungtv.application.service.AccountService;
import com.dungtv.application.service.ScoreService;
import com.dungtv.application.service.SubjectService;

@Controller
public class SubjectController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/admin/add-subject-form")
	public ModelAndView addSubjectForm() {
		LOGGER.info("Add form subject page");
		try {
			ModelAndView mav = new ModelAndView("admin/addOrUpdateSubjectForm");
			mav.addObject("titleAdd", true);
			mav.addObject("readonly", false);
			mav.addObject("subject", new Subject());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = "/admin/update-subject-form/{id}")
	public ModelAndView updateSubjectForm(@PathVariable String id) {
		LOGGER.info("Update subject page");
		try {
			ModelAndView mav = new ModelAndView("admin/addOrUpdateSubjectForm");
			mav.addObject("titleUpdate", true);
			mav.addObject("readonly", true);
			mav.addObject("subject", subjectService.getSubjectById(id));
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = "/admin/add-or-update-subject")
	public ModelAndView addSubject(@RequestParam(name = "id") String id, @RequestParam(name = "code") String code,
			@RequestParam(name = "name") String name, @RequestParam(name = "hours") int hours,
			@RequestParam(name = "timeStart") String timeStart, @RequestParam(name = "timeEnd") String timeEnd,
			@RequestParam(name = "number") int number) {

		LOGGER.info("Add or Update subject page");
		try {
			Subject subject = subjectService.getSubjectByCode(code);

			if (id.trim().isEmpty()) {
				// Check code exist
				if (subject != null) {
					ModelAndView mav = new ModelAndView("admin/addOrUpdateSubjectForm");
					mav.addObject("subject", new Subject(id, code, name, hours, number));
					mav.addObject("subjectCodeExist", true);
					return mav;
				}
				subject = new Subject();
				subject.setCode(code);
				subject.setName(name);
				subject.setHours(hours);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStart);
					subject.setTimeStart(date);
				} catch (Exception e) {

				}
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeEnd);
					subject.setTimeEnd(date);
				} catch (Exception e) {

				}
				subject.setNumber(number);
				subject.setStatus(SystemConstant.ACTIVE_STATUS);

				subjectService.addOrUpdateSubject(subject);
				ModelAndView mav = new ModelAndView("redirect:/admin/add-subject-form?alertAddSubjectSuccess=true");
				return mav;
			} else {
				subject.setName(name);
				subject.setHours(hours);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStart);
					subject.setTimeStart(date);
				} catch (Exception e) {

				}
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeEnd);
					subject.setTimeEnd(date);		
				} catch (Exception e) {

				}
				subject.setNumber(number);

				subjectService.addOrUpdateSubject(subject);
				ModelAndView mav = new ModelAndView("redirect:/admin/add-subject-form?alertUpdateSubjectSuccess=true");
				return mav;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/admin/delete-subject")
	public ModelAndView addSubject(@RequestParam(name = "id") String id) {
		LOGGER.info("Delete subject page");
		try {
			Subject subject = subjectService.getSubjectById(id);
			subject.setStatus(SystemConstant.INACTIVE_STATUS);
			subjectService.addOrUpdateSubject(subject);
			scoreService.closeRegisterByIdSubject(id, subject.getSemester());
			return new ModelAndView("redirect:/admin?alertDeleteSubject=true");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/admin/is-open-register-subject")
	public ModelAndView isOpenRegisterSubject() {
		LOGGER.info("Admin open register subject page");
		try {
			ModelAndView mav = new ModelAndView("admin/openRegisterSubject");
			mav.addObject("listSubjects", subjectService.getListAllSubjectOpen());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/admin/close-register-subject")
	public ModelAndView closeRegisterSubject(@RequestParam(name = "id") String id) {
		LOGGER.info("Admin close register subject page");
		try {
			//Close register subject then student can not cancel
			Subject subject = subjectService.getSubjectById(id);
			subject.setStatusRegister(SystemConstant.INACTIVE_STATUS);
			subjectService.addOrUpdateSubject(subject);
			//Close status register for set score, if not close then can not set score
			scoreService.closeRegisterByIdSubject(id, subject.getSemesterId());
			
			return new ModelAndView("redirect:/admin/is-open-register-subject?alertCloseRegisterSubject=true");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	//Display list subject is close
	@RequestMapping(value = "/admin/is-close-register-subject")
	public ModelAndView isCloseRegisterSubject() {
		LOGGER.info("Admin is close register subject page");
		try {
			ModelAndView mav = new ModelAndView("admin/closeRegisterSubject");
			mav.addObject("listSubjects", subjectService.getListAllSubjectClose());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/admin/open-register-subject")
	public ModelAndView openRegisterSubject(@RequestParam(name = "id") String id,
			@RequestParam(name = "semester") String semester,
			@RequestParam(name = "timeStart") String timeStart,
			@RequestParam(name = "timeEnd") String timeEnd,
			@RequestParam(name = "number") int number) {
		LOGGER.info("Admin open register subject page");
		try {
			Subject subject = subjectService.getSubjectById(id);
			UUID uuid = UUID.randomUUID();
			subject.setSemesterId(uuid.toString());
			subject.setSemester(semester);
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStart);
				subject.setTimeStart(date);
			} catch (Exception e) {

			}
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeEnd);
				subject.setTimeEnd(date);
			} catch (Exception e) {

			}
			subject.setNumber(number);
			subject.setNumberRegistered(SystemConstant.RESET_NUMBER_REGISTER);
			subject.setStatusRegister(SystemConstant.ACTIVE_STATUS);
			subjectService.addOrUpdateSubject(subject);
			return new ModelAndView("redirect:/admin/is-close-register-subject?alertOpenRegisterSubject=true");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/is-open-register-subject")
	public ModelAndView isOpenRegisterSubjectInUser() {
		LOGGER.info("Admin is open register subject page");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ModelAndView mav = new ModelAndView("user/registerSubject");
			mav.addObject("listSubjects", subjectService.getListAllSubjectOpen());
			mav.addObject("listIdSubjectsRegistered", scoreService.getListIdSubjectsRegistered(accountPrincipal.getAccount().getId()));
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/register-subject")
	public ModelAndView registerSubjectInUser(@RequestParam(name = "id") String id, @RequestParam(name = "semester") String semester) {
		LOGGER.info("Register subject page");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Subject subject = subjectService.getSubjectById(id);
			Score score = new Score();
			score.setAccount(accountService.getAccountById(accountPrincipal.getAccount().getId()));
			score.setSubject(subject);
			score.setSemester(semester);
			score.setSemesterId(subject.getSemesterId());
			score.setDate(new Date());
			score.setStatusRegister(SystemConstant.ACTIVE_STATUS);
			int number = subject.getNumberRegistered() + 1;
			subject.setNumberRegistered(number);
			
			ModelAndView mav = null;
			if(number <= subject.getNumber()) {
				scoreService.addOrUpdateScore(score);
				subjectService.addOrUpdateSubject(subject);
				mav= new ModelAndView("redirect:/is-open-register-subject?alertRegisterSubjectSuccess=true");
				//mav.addObject("alertRegisterSubjectSuccess", true);
			}else {
				mav = new ModelAndView("redirect:/is-open-register-subject?registerSubjectSuccess=false");
				//mav.addObject("alertRegisterSubjectSuccess", false);
			}
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/cancel-register-subject")
	public ModelAndView cancelRegisteredSubjectInUser(@RequestParam(name = "id") String id) {
		LOGGER.info("Cancel register subject page");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Subject subject = subjectService.getSubjectById(id);
			subject.setNumberRegistered(subject.getNumberRegistered() - 1);
			scoreService.deleteRegisterByIdUserAndIdSubject(accountPrincipal.getAccount().getId(), id);
			subjectService.addOrUpdateSubject(subject);
			ModelAndView mav = new ModelAndView("redirect:/is-open-register-subject?alertCancelRegisterSubjectSuccess=true");
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	//Show list subject registered
	@RequestMapping(value = "/is-registered-subject")
	public ModelAndView registeredSubject() {
		LOGGER.info("Is register subject page");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
			ModelAndView mav = new ModelAndView("user/isRegisteredSubject");
			mav.addObject("listRegisteredSubjects", scoreService.getListRegisteredSubjectByIdUser(accountPrincipal.getAccount().getId()));
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
}
