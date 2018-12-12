package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {
	boolean deleteUser(Integer id);
	
	UserInfo findByEmail(String email);
	
	List<UserInfo> loadUsers(Integer offset, Integer maxResults);
	
	UserInfo findByIdUser(Integer id);
	
	Long count();
}
