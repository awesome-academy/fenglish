package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {
	
	boolean deleteUserById(Integer id);
	
	List<UserInfo> loadUsers(Integer offset, Integer maxResult);
	
	Long count();
	
	UserInfo findUserById(Integer key);
	
	User saveUserOrUpdate(UserInfo userInfo);

}
