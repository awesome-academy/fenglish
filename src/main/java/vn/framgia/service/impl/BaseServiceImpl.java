package vn.framgia.service.impl;

import vn.framgia.dao.LevelDAO;
import vn.framgia.dao.SubjectDAO;
import vn.framgia.dao.UserDAO;

public class BaseServiceImpl {
	
	protected UserDAO userDAO;
	protected SubjectDAO subjectDAO;
	protected LevelDAO levelDAO;

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

	public LevelDAO getLevelDAO() {
		return levelDAO;
	}

	public void setLevelDAO(LevelDAO levelDAO) {
		this.levelDAO = levelDAO;
	}
	
}
