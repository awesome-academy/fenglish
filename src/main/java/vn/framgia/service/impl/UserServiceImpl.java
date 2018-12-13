package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.List;

import vn.framgia.bean.UserInfo;
import vn.framgia.helper.UserConvertHelper;
import vn.framgia.model.User;
import vn.framgia.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public User findById(Serializable key) {
		return userDAO.findById(key);
	}

	@Override
	public User saveOrUpdate(User entity) {
		return userDAO.saveOrUpdate(entity);
	}

	@Override
	public boolean delete(User entity) {
		try {
			userDAO.delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<UserInfo> loadUsers(Integer offset, Integer maxResult) {
		return UserConvertHelper.convertUserToUserInfo(userDAO.loadUsers(offset, maxResult));
	}

	@Override
	public Long count() {
		return userDAO.count();
	}

}
