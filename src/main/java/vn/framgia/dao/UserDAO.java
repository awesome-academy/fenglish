package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.User;

public interface UserDAO extends BaseDAO<Integer, User> {

	User findByEmail(String email);

	List<User> loadUsers(Integer offset, Integer maxResult);

	Long count();
}
