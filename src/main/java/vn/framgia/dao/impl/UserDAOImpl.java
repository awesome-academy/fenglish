package vn.framgia.dao.impl;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.UserDAO;
import vn.framgia.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}

	public UserDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	public User findUserByEmail(String email) {
		logger.info("email: " + email);
		User user = (User) getSession().createQuery("from User u where u.email = :email").setParameter("email", email)
				.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll(int page, int userPerPage) {
		logger.info("Get list user");
		return (List<User>) getSession().createQuery("from User u where u.deleted = 0").setFirstResult(page)
				.setMaxResults(userPerPage).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() {
		logger.info("Get list user");
		return (List<User>) getSession().createQuery("from User u where u.deleted = 0").getResultList();

	}

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

	@Override
	public User findUserByEmailAndUsingLock(String email, LockModeType lockMode) {
		String hql = "FROM User a WHERE a.email = :email AND a.deleted = 0";
		return (User) getSession().createQuery(hql).setParameter("email", email).setLockMode(lockMode)
				.getSingleResult();
	}

	@Override
	public User findByFacebookId(String facebookId) {
		String hql = "SELECT a "
				+ "FROM User a "
				+ "INNER JOIN FacebookAccount b ON a.id = b.user.id "
				+ "WHERE a.deleted = 0 "
				+ "AND b.facebookId = :facebookId";
		
		return getSession().createQuery(hql, User.class).setParameter("facebookId", facebookId).getSingleResult();
	}

}
