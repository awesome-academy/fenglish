package vn.framgia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.UserDAO;
import vn.framgia.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers(Integer offset, Integer maxResult) {
		Query query = getSession().createQuery("FROM User a WHERE a.deleted = 0");

		if (maxResult == null) {
			maxResult = 10;
		}

		if (offset == null || offset < 1) {
			query.setFirstResult(0);
		} else {
			query.setFirstResult((offset - 1) * maxResult);
		}

		query.setMaxResults(maxResult);

		return query.getResultList();
	}

	@Override
	public Long count() {
		String sql = "SELECT COUNT(*) FROM User u WHERE u.deleted = 0";
		return (long) getSession().createQuery(sql).getSingleResult();
	}

	@Override
	public boolean deleteUser(User user) {
		String hql = "UPDATE User u SET u.deleted = 1 WHERE u.id = :id";
		getSession().createQuery(hql).setParameter("id", user.getId()).executeUpdate();
		
		return true;
	}
}
