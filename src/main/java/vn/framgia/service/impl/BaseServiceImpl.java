package vn.framgia.service.impl;

import vn.framgia.dao.UserDAO;

public class BaseServiceImpl {
	
	protected UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
