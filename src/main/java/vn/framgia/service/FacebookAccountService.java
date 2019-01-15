package vn.framgia.service;

import com.restfb.types.User;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.FacebookAccount;

public interface FacebookAccountService extends BaseService<Integer, FacebookAccount> {
	
	boolean updateFBSocial(User userFB, UserInfo user);
	
}