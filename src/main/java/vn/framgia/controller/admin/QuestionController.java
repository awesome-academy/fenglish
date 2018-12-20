package vn.framgia.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.framgia.bean.LevelInfo;
import vn.framgia.bean.QuestionInfo;
import vn.framgia.bean.SubjectInfo;
import vn.framgia.helper.QuestionConvertHelper;
import vn.framgia.service.LevelService;
import vn.framgia.service.QuestionService;
import vn.framgia.service.SubjectService;

@Controller
@RequestMapping("/admin")
public class QuestionController {
	private static final int pageSize = 10;
	@Autowired
	QuestionService questionService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	LevelService levelService;

	@RequestMapping(value = "/questions/page={pageNumber}", method = RequestMethod.GET)
	public String index(@PathVariable("pageNumber") Integer pageNumber, Model model) {

		List<QuestionInfo> listQuestionInfo = QuestionConvertHelper
				.convertListQuestionToListQuestionInfo(questionService.listAll(pageSize, pageNumber));
		model.addAttribute("count", questionService.countListAll());
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("listQuestion", listQuestionInfo);

		return "/questions/index";
	}

	@RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		if (questionService.deleteQuestion(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "msg.user.deletesuccess");
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("css", "msg.user.deletefail");
		}

		return "redirect:/admin/";
	}

	@RequestMapping(value = "/questions/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		QuestionInfo questionInfo = QuestionConvertHelper
				.convertQuestionToQuestionInfo(questionService.findQuestionById(id));
		List<SubjectInfo> listSubjectInfo = subjectService.loadSubjects(1, pageSize);
		List<LevelInfo> listLevelInfo = levelService.loadLevels(1, pageSize);
		model.addAttribute("questionForm", questionInfo);
		model.addAttribute("listSubjectInfo", listSubjectInfo);
		model.addAttribute("listLevelInfo", listLevelInfo);
		return "/questions/edit";
	}
	
	@RequestMapping(value = "/questions/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("questionForm") QuestionInfo questionForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "/questions/edit";
		}
		questionService.saveOrUpdate(questionForm);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "msg.user.updatesuccess");

		return "redirect:/admin/questions/page=1";
	}
}
