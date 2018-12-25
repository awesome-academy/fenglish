package vn.framgia.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {

	User findByEmail(String email);

	List<User> findAll(int page, int userPerPage);

	boolean deleteUserById(Integer id);

	List<UserInfo> loadUsers(Integer offset, Integer maxResult);

	Long count();

	UserInfo findUserById(Integer key);

	UserInfo saveUserOrUpdate(UserInfo userInfo);

	boolean saveUserAfferRegister(User user, String token);

	boolean updateConfirmRegister(String email, String token);
	
	UserInfo updateUserAndChangeAvatar(UserInfo userInfo, MultipartFile file);
}
