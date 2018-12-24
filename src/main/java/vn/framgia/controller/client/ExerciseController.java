package vn.framgia.controller.client;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.bean.QuestionInfo;
import vn.framgia.bean.SubjectInfo;
import vn.framgia.controller.BaseController;
import vn.framgia.model.Subject;

@Controller
@RequestMapping("/exercises")
public class ExerciseController extends BaseController {

	private static final int totalQuestion = 10;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showExercise(@PathVariable("id") Integer id, Model model, Authentication authentication) {
		
		if (exerciseService.checkUserAuthentication(id, authentication.getName())) {
			SubjectInfo subject = subjectService.getSubjectInExercise(id);
			List<QuestionInfo> questions = questionService.getListQuestionByIdExercise(id);
			
			model.addAttribute("subject", subject);
			model.addAttribute("questions", questions);
			
			return "/client/exercise";
		}
		
		return "redirect:/";
	}

	@RequestMapping(value = "/create/{idSubject}", method = RequestMethod.GET)
	public String createExercise(@PathVariable("idSubject") Integer idSubject, Authentication authentication) {

		if (authentication != null) {
			Subject subject = subjectService.findById(idSubject);
			ExerciseInfo exerciseInfo = exerciseService.createExercise(authentication.getName(), subject,
					totalQuestion);

			return "redirect:/exercises/" + exerciseInfo.getId();
		}

		return "redirect:/";
	}

}
