package vn.framgia.dao.impl;

import java.util.List;

import vn.framgia.dao.ExerciseDAO;
import vn.framgia.dao.GenericDAO;
import vn.framgia.model.Exercise;

public class ExerciseDAOImpl extends GenericDAO<Integer, Exercise> implements ExerciseDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercise> findListExerciseByIdUser(Integer idUser) {
		String hql = "SELECT b "
				+ "FROM User a "
				+ "INNER JOIN Exercise b ON a.id = b.user.id "
				+ "WHERE a.id = :id "
				+ "AND b.submitted = 1";
		return getSession().createQuery(hql).setParameter("id", idUser).getResultList();
	}

}
