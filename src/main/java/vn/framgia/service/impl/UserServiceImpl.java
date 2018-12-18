package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.bean.UserInfo;
import vn.framgia.dao.UserDAO;
import vn.framgia.helper.UserConvertHelper;
import vn.framgia.model.User;
import vn.framgia.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public User findByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@Override

	public List<User> findAll(int page, int userPerPage) {
		// TODO Auto-generated method stub
		try {
			return userDAO.listAll(page, userPerPage);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public User findById(Serializable key) {
		try {
			return userDAO.findById(key);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
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

	@Override
	public boolean saveUserAfferRegister(User user, String token) {
		try {
			User userInDB = userDAO.findUserByEmail(user.getEmail());
			if (userInDB == null) {
				user.setPasswordResetToken(token);
				user.setRole("ROLE_USER");
				userDAO.saveOrUpdate(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean confirmRegister(String email, String token) {
		try {
			User userInDB = userDAO.findUserByEmail(email);
			if (userInDB == null)
				return false;
			if (userInDB.getRole().equals("ROLE_UNCONFIRM") && userInDB.getPasswordResetToken().equals(token)) {
				userInDB.setRole("ROLE_USER");
				userDAO.saveOrUpdate(userInDB);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
