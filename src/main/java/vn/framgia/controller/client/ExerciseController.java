package vn.framgia.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import vn.framgia.model.ExerciseDetail;
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
			model.addAttribute("id_exercise", id);

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

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String markExercise(HttpServletRequest request, Model model) {
		Integer idExercise = Integer.parseInt(request.getParameter("id_exercise"));

		ExerciseInfo exerciseInfo = exerciseService.findExerciseById(idExercise);

		if (exerciseInfo == null) {
			model.addAttribute("score", 0);
			model.addAttribute("total_question", 0);
			return "/client/exercise/result";
		}
		
		int score = 0;
		int totalQuestion = exerciseInfo.getExerciseDetails().size();
		
		List<ExerciseDetail> exerciseDetails = exerciseInfo.getExerciseDetails();

		for (ExerciseDetail exerciseDetail : exerciseDetails) {
			String answerPatern = "answer[" + idExercise + "][" + exerciseDetail.getQuestion().getId() + "]";
			String answerRequest = request.getParameter(answerPatern);

			if (answerRequest == null) {
				continue;
			}
			
			Integer answer = Integer.parseInt(answerRequest);
			exerciseDetail.setAnswer(answer);

			if (answer == exerciseDetail.getQuestion().getCorrectAnswer()) {
				score++;
			}
		}
		

		model.addAttribute("score", score);
		model.addAttribute("total_question", totalQuestion);

		return "/client/exercise/result";
	}

}
