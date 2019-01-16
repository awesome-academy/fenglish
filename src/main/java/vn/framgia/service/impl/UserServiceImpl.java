package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.LockModeType;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import vn.framgia.bean.UserInfo;
import vn.framgia.helper.FileUploadHelper;
import vn.framgia.helper.ROLES;
import vn.framgia.helper.UserConvertHelper;
import vn.framgia.model.FacebookAccount;
import vn.framgia.model.User;
import vn.framgia.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@Override
	public User findByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@Override
	public List<User> findAll(int page, int userPerPage) {
		try {
			return userDAO.listAll(page, userPerPage);
		} catch (Exception e) {
			logger.error("Error in findAll: " + e.getMessage());
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<User> findAll() {
		try {
			return userDAO.listAll();
		} catch (Exception e) {
			logger.error("Error in findAll: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public User findById(Serializable key) {
		try {
			return userDAO.findById(key);
		} catch (Exception e) {
			logger.error("Error in findById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public User saveOrUpdate(User entity) {
		try {
			User lockEntity = userDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, lockEntity);
			return userDAO.saveOrUpdate(lockEntity);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(User entity) {
		try {
			User lockEntity = userDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, lockEntity);
			userDAO.delete(lockEntity);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
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

	@Override
	public UserInfo findUserById(Integer key) {
		try {
			return UserConvertHelper.convertSingleUserToUserInfo(userDAO.findById(key));
		} catch (Exception e) {
			logger.error("Error in findUserById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean deleteUserById(Integer id) {
		try {
			User entity = userDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);
			return userDAO.deleteUser(entity);
		} catch (Exception e) {
			logger.error("Error in deleteUserById: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public UserInfo saveUserOrUpdate(UserInfo userInfo) {
		try {
			User user = userDAO.findByIdUsingLock(userInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			UserConvertHelper.convertSingleUserInfoToUser(user, userInfo);
			return UserConvertHelper.convertSingleUserToUserInfo(userDAO.saveOrUpdate(user));
		} catch (Exception e) {
			logger.error("Error in saveUserOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean saveUserAfferRegister(User user, String token) {
		try {
			User userInDB = userDAO.findUserByEmail(user.getEmail());
			if (userInDB == null) {
				user.setPasswordResetToken(token);
				user.setRole(ROLES.UNCONFIRM.toString());
				userDAO.saveOrUpdate(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("Error in saveUserAfferRegister: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateConfirmRegister(String email, String token) {
		try {
			User userInDB = userDAO.findUserByEmailAndUsingLock(email, LockModeType.PESSIMISTIC_WRITE);
			if (userInDB == null)
				return false;
			if (userInDB.getRole().equals(ROLES.UNCONFIRM.toString())
					&& userInDB.getPasswordResetToken().equals(token)) {
				userInDB.setRole(ROLES.USER.toString());
				userDAO.saveOrUpdate(userInDB);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("Error in confirmRegister: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public UserInfo updateUserAndChangeAvatar(UserInfo userInfo, MultipartFile file) {
		try {
			// Lock Object
			User user = userDAO.findByIdUsingLock(userInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			UserConvertHelper.convertSingleUserInfoToUser(user, userInfo);

			// Upload Avatar
			user.setAvatar(fileUploadHelper.upFile(file));

			// Update Profile
			userDAO.saveOrUpdate(user);

			return UserConvertHelper.convertSingleUserToUserInfo(user);
		} catch (Exception e) {
			logger.error("Error in updateUserAndChangeAvatar: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public UserInfo findUserByFacebookId(String facebookId) {
		try {
			return UserConvertHelper.convertSingleUserToUserInfo(userDAO.findByFacebookId(facebookId));
		} catch (Exception e) {
			logger.error("Error in findUserByFacebookId: " + e.getMessage());
			return null;
		}
	}

	@Override
	public UserInfo createNewFBSocial(com.restfb.types.User userFB) {
		try {
			// Create new User
			User user = new User();
			user.setEmail("");
			user.setFullname(userFB.getName());
			user.setGender(false);
			user.setRole(ROLES.USER.toString());
			user = userDAO.saveOrUpdate(user);
			
			// Create new FacebookAccount
			FacebookAccount fbAccount = new FacebookAccount();
			fbAccount.setUser(user);
			fbAccount.setFacebookId(userFB.getId());
			facebookAccountDAO.saveOrUpdate(fbAccount);
			
			return UserConvertHelper.convertSingleUserToUserInfo(user);
		} catch (Exception e) {
			logger.error("Error in createNewFBSocial: " + e.getMessage());
			throw e;
		}
	}

}