package vn.framgia.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.framgia.bean.SubjectInfo;
import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/admin")
public class SubjectsController extends BaseController {

	private static final int maxResult = 10;

	@RequestMapping(value = "/subjects/page={offset}", method = RequestMethod.GET)
	public String showSubject(@PathVariable("offset") Integer offset, Model model) {

		List<SubjectInfo> subjectInfos = subjectService.loadSubjects(offset, maxResult);
		model.addAttribute("subjects", subjectInfos);
		model.addAttribute("count", subjectService.count());
		model.addAttribute("offset", offset);

		return "/subjects/index";
	}

	@RequestMapping(value = "/subjects/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSubject(@PathVariable("id") Integer id) {

		if (subjectService.deleteSubjectById(id)) {
			return "success";
		}

		return "fail";
	}

	@RequestMapping(value = "/subjects/create", method = RequestMethod.GET)
	public String showForm(Model model) {

		model.addAttribute("subjectForm", new SubjectInfo());

		return "/subjects/create";
	}

	@RequestMapping(value = "/subjects/create", method = RequestMethod.POST)
	public String createNewSubject(@Valid @ModelAttribute("subjectForm") SubjectInfo subjectForm,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/subjects/create";
		}
		
		subjectService.saveOrUpdateSubject(subjectForm);

		return "redirect:/admin/subjects";
	}

}
