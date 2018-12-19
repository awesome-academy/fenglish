package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.helper.ExerciseConvertHelper;
import vn.framgia.model.Exercise;
import vn.framgia.model.ExerciseDetail;
import vn.framgia.model.Question;
import vn.framgia.model.Subject;
import vn.framgia.model.User;
import vn.framgia.service.ExerciseService;

public class ExerciseServiceImpl extends BaseServiceImpl implements ExerciseService {

	@Override
	public Exercise findById(Serializable key) {
		return exerciseDAO.findById(key);
	}

	@Override
	public Exercise saveOrUpdate(Exercise entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Exercise entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ExerciseInfo createExercise(String authName, Subject subject, Integer totalQuestion) {
		try {
			// Create new exercise
			Exercise exercise = new Exercise();
			User user = userDAO.findUserByEmail(authName);
			exercise.setUser(user);
			exercise.setSubject(subject);

			Exercise exercisePersist = exerciseDAO.saveOrUpdate(exercise);

			// Random question
			List<Question> questions = questionDAO.getQuestionByIdSubject(subject.getId());
			Collections.shuffle(questions);

			if (totalQuestion > questions.size()) {
				totalQuestion = questions.size();
			}

			// Add question into exercise
			for (Question question : questions.subList(0, totalQuestion)) {
				ExerciseDetail exerciseDetail = new ExerciseDetail();
				exerciseDetail.setExercise(exercisePersist);
				question.setCorrectAnswer(null);
				exerciseDetail.setQuestion(question);
				exerciseDetailDAO.saveOrUpdate(exerciseDetail);
			}

			ExerciseInfo exerciseInfo = ExerciseConvertHelper.convertSingleExerciseToExerciseInfo(exercisePersist);

			return exerciseInfo;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean checkUserAuthentication(Integer id, String authName) {
		try {
			Exercise exercise = exerciseDAO.findById(id);
			if (authName.equals(exercise.getUser().getEmail())) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
