package vn.framgia.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.beans.BeanUtils;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.FacebookAccount;
import vn.framgia.service.FacebookAccountService;

public class FacebookAccountServiceImpl extends BaseServiceImpl implements FacebookAccountService {

	private static final Logger logger = Logger.getLogger(FacebookAccountServiceImpl.class);

	@Override
	public FacebookAccount findById(Serializable key) {
		try {
			return facebookAccountDAO.findById(key);
		} catch (Exception e) {
			logger.error("Error in findById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public FacebookAccount saveOrUpdate(FacebookAccount entity) {
		try {
			FacebookAccount fbAccount = facebookAccountDAO.findByIdUsingLock(entity.getUserId(),
					LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, fbAccount);
			return facebookAccountDAO.saveOrUpdate(fbAccount);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(FacebookAccount entity) {
		try {
			FacebookAccount fbAccount = facebookAccountDAO.findByIdUsingLock(entity.getUserId(),
					LockMode.PESSIMISTIC_WRITE);
			facebookAccountDAO.delete(fbAccount);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean updateFBSocial(com.restfb.types.User userFB, UserInfo user) {
		try {
			FacebookAccount fbAccount = new FacebookAccount();
			fbAccount.setUserId(user.getId());
			fbAccount.setFacebookId(userFB.getId());
			facebookAccountDAO.saveOrUpdate(fbAccount);
			
			return true;
		} catch (Exception e) {
			logger.error("Error in updateFBSocial: " + e.getMessage());
			throw e;
		}
	}

}
