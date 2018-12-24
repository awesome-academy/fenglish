package vn.framgia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.SubjectDAO;
import vn.framgia.model.Subject;

public class SubjectDAOImpl extends GenericDAO<Integer, Subject> implements SubjectDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> loadSubject(Integer offset, Integer maxResult) {
		Query query = getSession().createQuery("FROM Subject a WHERE a.deleted = 0");
		
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
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM Subject a WHERE a.deleted = 0")
				.getSingleResult();
	}

	@Override
	public void deleteSubject(Integer key) {
		String hql = "UPDATE Subject a SET a.deleted = 1 WHERE a.id = :id";
		getSession().createQuery(hql).setParameter("id", key).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> loadAllSubject() {
		String hql = "FROM Subject a WHERE a.deleted = 0";
		return getSession().createQuery(hql).getResultList();
	}

	@Override
	public Subject getSubjectInExercise(Integer idExercise) {
		String hql = "SELECT a "
				+ "FROM Subject a "
				+ "INNER JOIN Exercise b ON a.id = b.subject.id "
				+ "WHERE b.id = :idExercise";
		return (Subject) getSession().createQuery(hql).setParameter("idExercise", idExercise).getSingleResult();
	}

}
