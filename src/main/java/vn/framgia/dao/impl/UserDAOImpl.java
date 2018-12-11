package vn.framgia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.UserDAO;
import vn.framgia.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	@Override
	public User findByEmail(String email) {
		String hql = "FROM User a WHERE a.email = :email";
		return (User) getSession().createQuery(hql).setParameter("email", email).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers(Integer offset, Integer maxResult) {
		Query query = getSession().createQuery("FROM User a WHERE a.deleted = 0");

		if (offset == null || offset < 1) {
			query.setFirstResult(0);
		} else {
			query.setFirstResult((offset - 1) * maxResult);
		}

		if (maxResult != null) {
			query.setMaxResults(maxResult);
		} else {
			query.setMaxResults(10);
		}

		return query.getResultList();
	}

	@Override
	public Long count() {
		return (long) getSession().createQuery("FROM User").getResultList().size();
	}

}
