package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LockMode;

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
	public User saveOrUpdate(User entity) throws IllegalAccessException, InvocationTargetException {
		User lockEntity = userDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
		BeanUtils.copyProperties(lockEntity, entity);
		return userDAO.saveOrUpdate(lockEntity);
	}

	@Override
	public boolean delete(User entity) throws IllegalAccessException, InvocationTargetException {
		User lockEntity = userDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
		BeanUtils.copyProperties(lockEntity, entity);
		userDAO.delete(lockEntity);
		return true;
	}

	@Override
	public List<UserInfo> loadUsers(Integer offset, Integer maxResult) {
		return UserConvertHelper.convertUserToUserInfo(userDAO.loadUsers(offset, maxResult));
	}

	@Override
	public Long count() {
		return userDAO.count();
	}

	@Override
	public UserInfo findUserById(Integer key) {
		return UserConvertHelper.convertSingleUserToUserInfo(userDAO.findById(key));
	}

	@Override
	public boolean deleteUserById(Integer id) {
		try {
			User entity = userDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);
			return userDAO.deleteUser(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User saveUserOrUpdate(UserInfo userInfo) {
		User user = userDAO.findByIdUsingLock(userInfo.getId(), LockMode.PESSIMISTIC_WRITE);
		
		try {
			UserConvertHelper.convertSingleUserInfoToUser(user, userInfo);
			return userDAO.saveOrUpdate(user);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
