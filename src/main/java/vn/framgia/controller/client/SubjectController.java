package vn.framgia.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.service.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showSubjects(Model model) {
		model.addAttribute("subjects", subjectService.loadAllSubject());
		return "/client/subjects/index";
	}
}
