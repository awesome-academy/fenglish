package vn.framgia.controller.client;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.bean.QuestionInfo;
import vn.framgia.controller.BaseController;
import vn.framgia.model.Subject;

@Controller
@RequestMapping("/exercises")
public class ExerciseController extends BaseController {

	private static final int totalQuestion = 10;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showExercise(@PathVariable("id") Integer id, Model model, Authentication authentication) {
		
		if (exerciseService.checkUserAuthentication(id, authentication.getName())) {
			List<QuestionInfo> questions = questionService.getListQuestionByIdExercise(id);
			model.addAttribute("questions", questions);

			return "/client/exercise";
		}
		
		return "redirect:/";
	}

	@RequestMapping(value = "/create/{idSubject}", method = RequestMethod.GET)
	public String createExercise(@PathVariable("idSubject") Integer idSubject, RedirectAttributes redirectAttributes,
			Authentication authentication) {

		if (authentication != null) {
			Subject subject = subjectService.findById(idSubject);
			ExerciseInfo exerciseInfo = exerciseService.createExercise(authentication.getName(), subject,
					totalQuestion);

			redirectAttributes.addFlashAttribute("subjectName", subject.getSubjectName());

			return "redirect:/exercises/" + exerciseInfo.getId();
		}

		return "redirect:/";
	}

}
