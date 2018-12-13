package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {
	
	List<UserInfo> loadUsers(Integer offset, Integer maxResult);
	
	Long count();
	
}
