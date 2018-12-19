package vn.framgia.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/subjects")
public class SubjectController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showSubjects(Model model) {
		model.addAttribute("subjects", subjectService.loadAllSubject());
		return "/client/subjects/index";
	}
}
