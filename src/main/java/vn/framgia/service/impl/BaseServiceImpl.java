package vn.framgia.service.impl;

import vn.framgia.dao.ExerciseDAO;
import vn.framgia.dao.ExerciseDetailDAO;
import vn.framgia.dao.QuestionDAO;
import vn.framgia.dao.LevelDAO;
import vn.framgia.dao.PostDAO;
import vn.framgia.dao.SubjectDAO;
import vn.framgia.dao.UserDAO;

public class BaseServiceImpl {

	protected UserDAO userDAO;
	protected SubjectDAO subjectDAO;
	protected ExerciseDAO exerciseDAO;
	protected ExerciseDetailDAO exerciseDetailDAO;
	protected QuestionDAO questionDAO;
	protected LevelDAO levelDAO;
	protected PostDAO postDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public ExerciseDetailDAO getExerciseDetailDAO() {
		return exerciseDetailDAO;
	}

	public void setExerciseDetailDAO(ExerciseDetailDAO exerciseDetailDAO) {
		this.exerciseDetailDAO = exerciseDetailDAO;
	}

	public ExerciseDAO getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAO exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	public LevelDAO getLevelDAO() {
		return levelDAO;
	}

	public void setLevelDAO(LevelDAO levelDAO) {
		this.levelDAO = levelDAO;
	}

	public PostDAO getPostDAO() {
		return postDAO;
	}

	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}

}
