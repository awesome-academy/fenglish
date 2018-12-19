package vn.framgia.service.impl;

import vn.framgia.dao.SubjectDAO;
import vn.framgia.dao.UserDAO;

public class BaseServiceImpl {
	
	protected UserDAO userDAO;
	protected SubjectDAO subjectDAO;

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
	
}
