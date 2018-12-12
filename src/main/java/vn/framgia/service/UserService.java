package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {

	User findByEmail(String email);

	List<User> findAll(int page, int userPerPage);

	boolean deleteUserById(Integer id);

	List<UserInfo> loadUsers(Integer offset, Integer maxResult);

	Long count();

	UserInfo findUserById(Integer key);

	User saveUserOrUpdate(UserInfo userInfo);

	boolean saveUserAfferRegister(User user, String token);

	boolean confirmRegister(String email, String token);

}
