package vn.framgia.dao;

import java.util.List;

import javax.persistence.LockModeType;

import vn.framgia.model.User;

public interface UserDAO extends BaseDAO<Integer, User> {
	User findUserByEmail(String email);

	List<User> listAll(int page, int userPerPage);

	List<User> loadUsers(Integer offset, Integer maxResult);

	Long count();
	
	boolean deleteUser(User user);
	
	User findUserByEmailAndUsingLock(String email, LockModeType lockMode);
}
