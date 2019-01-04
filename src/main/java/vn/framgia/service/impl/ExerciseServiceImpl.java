package vn.framgia.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.BeanUtils;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.helper.ExerciseConvertHelper;
import vn.framgia.model.Exercise;
import vn.framgia.model.ExerciseDetail;
import vn.framgia.model.Question;
import vn.framgia.model.Subject;
import vn.framgia.model.User;
import vn.framgia.service.ExerciseService;

public class ExerciseServiceImpl extends BaseServiceImpl implements ExerciseService {

	private static final Logger logger = Logger.getLogger(ExerciseServiceImpl.class);

	@Override
	public Exercise findById(Serializable key) {
		return exerciseDAO.findById(key);
	}

	@Override
	public Exercise saveOrUpdate(Exercise entity) {
		try {
			Exercise lockEntity = exerciseDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, lockEntity);
			return exerciseDAO.saveOrUpdate(lockEntity);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Exercise entity) {
		try {
			Exercise lockEntity = exerciseDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, lockEntity);
			exerciseDAO.delete(lockEntity);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public ExerciseInfo createExercise(String authName, Subject subject, Integer totalQuestion) {
		try {
			// Create new exercise
			Exercise exercise = new Exercise();
			User user = userDAO.findUserByEmail(authName);
			exercise.setUser(user);
			exercise.setSubject(subject);
			exercise.setCreateTime(new Timestamp(System.currentTimeMillis()));
			exercise.setSubmitted(false);
			exercise.setDeleted(false);

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
				exerciseDetail.setQuestion(question);
				exerciseDetailDAO.saveOrUpdate(exerciseDetail);
			}

			ExerciseInfo exerciseInfo = ExerciseConvertHelper.convertSingleExerciseToExerciseInfo(exercisePersist);

			return exerciseInfo;
		} catch (Exception e) {
			logger.error("Error in createExercise: " + e.getMessage());
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
			logger.error("Error in checkUserAuthentication: " + e.getMessage());
			return false;
		}
	}

	@Override
	public ExerciseInfo findExerciseById(Integer id) {
		try {
			ExerciseInfo exerciseInfo = ExerciseConvertHelper
					.convertSingleExerciseToExerciseInfo(exerciseDAO.findById(id));
			List<ExerciseDetail> exerciseDetails = exerciseDetailDAO.findExerciseDetails(id);
			List<Question> questions = questionDAO.getQuestionByIdExercise(id);

			for (int i = 0; i < exerciseDetails.size(); i++) {
				exerciseDetails.get(i).setQuestion(questions.get(i));
			}
			
			Hibernate.initialize(exerciseInfo.getSubject());
			exerciseInfo.setExerciseDetails(exerciseDetails);

			return exerciseInfo;
		} catch (Exception e) {
			logger.error("Error in findExerciseById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public ExerciseInfo saveOrUpdateExercise(ExerciseInfo exerciseInfo) {
		try {
			Exercise lockEntity = exerciseDAO.findByIdUsingLock(exerciseInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			exerciseInfo.setExerciseDetails(Collections.emptyList());
			ExerciseConvertHelper.convertSingleExerciseInfoToExercise(exerciseInfo, lockEntity);
			return ExerciseConvertHelper.convertSingleExerciseToExerciseInfo(exerciseDAO.saveOrUpdate(lockEntity));
		} catch (Exception e) {
			logger.error("Error in saveOrUpdateExercise: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<ExerciseInfo> findListExerciseByIdUser(Integer idUser) {
		try {
			List<ExerciseInfo> exerciseInfos = ExerciseConvertHelper
					.convertExerciseToExerciseInfo(exerciseDAO.findListExerciseByIdUser(idUser));

			for (int i = 0; i < exerciseInfos.size(); i++) {
				exerciseInfos.set(i, findExerciseById(exerciseInfos.get(i).getId()));
			}

			return exerciseInfos;
		} catch (Exception e) {
			logger.error("Error in findListExerciseByIdUser: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
