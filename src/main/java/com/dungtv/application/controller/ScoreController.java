package com.dungtv.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dungtv.application.domain.AccountPrincipal;
import com.dungtv.application.domain.Score;
import com.dungtv.application.service.ScoreService;
import com.dungtv.application.service.SubjectService;

@Controller
public class ScoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/admin/score")
	public ModelAndView score() {
		LOGGER.info("Admin score page");
		try {
			ModelAndView mav = new ModelAndView("admin/score");
			mav.addObject("listSemester", scoreService.getListSemester());
			mav.addObject("listSubjects", subjectService.getListSubjects());
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = "/admin/score/search")
	public String scoreSearch(@RequestParam(name = "semester") String semester,
			@RequestParam(name = "subjectId") String subjectId, Model model) {
		LOGGER.info("Admin score search page");
		try {
			List<Score> list = null;
			if (semester.equalsIgnoreCase("all") && subjectId.equalsIgnoreCase("all")) {
				list = scoreService.getAll();
			}
			if (semester.equalsIgnoreCase("all") && !subjectId.equalsIgnoreCase("all")) {
				list = scoreService.getListScoreByIdSubject(subjectId);
			}
			if (!semester.equalsIgnoreCase("all") && subjectId.equalsIgnoreCase("all")) {
				list = scoreService.getListScoreBySemesterName(semester);
			}
			if (!semester.equalsIgnoreCase("all") && !subjectId.equalsIgnoreCase("all")) {
				list = scoreService.getListScoreByNameSemesterAndIdSubject(semester, subjectId);
			}
			model.addAttribute("list", list);
			return "forward:/admin/score";
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	@RequestMapping(value = "/admin/score/update")
	public String scoreUpdate(@RequestParam(name = "id") String id,
			@RequestParam(name = "number") int number,
			@RequestParam(name = "semester") String semester,
			@RequestParam(name = "subjectId") String subjectId) {
		LOGGER.info("Admin score update");
		try {
			Score score = scoreService.getScoreById(id);
			if(number >= 0 && number <= 10) {
				score.setScore(number);
				scoreService.addOrUpdateScore(score);
			}
			return "redirect:/admin/score/search?semester="+semester+"&subjectId="+subjectId+"&updateSuccess=true";
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value = "/score")
	public ModelAndView scoreUser() {
		LOGGER.info("User score");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
			ModelAndView mav = new ModelAndView("user/score");
			mav.addObject("list", scoreService.getListSemesterByIdUser(accountPrincipal.getAccount().getId()));		
			return mav;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	@RequestMapping(value = "/score/search")
	public String scoreUserSearch(@RequestParam(name = "semester") String semester, Model model) {
		LOGGER.info("User score search");
		try {
			AccountPrincipal accountPrincipal =
					 (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
			List<Score> list = null;
			if (!semester.equalsIgnoreCase("all")) {
				list = scoreService.getListScoreByNameSemesterAndAccountId(semester, accountPrincipal.getAccount().getId());			
			}else {
				list = scoreService.getListSemesterByIdUser(accountPrincipal.getAccount().getId());
			}
			model.addAttribute("listScore", list);	
			return "forward:/score";
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
}
