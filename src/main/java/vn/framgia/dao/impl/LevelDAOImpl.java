package vn.framgia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.LevelDAO;
import vn.framgia.model.Level;

public class LevelDAOImpl extends GenericDAO<Integer, Level> implements LevelDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Level> loadLevel(Integer offset, Integer maxResult) {
		Query query = getSession().createQuery("FROM Level a WHERE a.deleted = 0");

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Level> loadAllLevel() {
		String hql = "FROM Level a WHERE a.deleted = 0";
		return getSession().createQuery(hql).getResultList();
	}

}
