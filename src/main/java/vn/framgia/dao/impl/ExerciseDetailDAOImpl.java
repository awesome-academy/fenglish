package vn.framgia.dao.impl;

import java.util.List;

import vn.framgia.dao.ExerciseDetailDAO;
import vn.framgia.dao.GenericDAO;
import vn.framgia.model.ExerciseDetail;

public class ExerciseDetailDAOImpl extends GenericDAO<Integer, ExerciseDetail> implements ExerciseDetailDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ExerciseDetail> findExerciseDetails(Integer idExercise) {
		String hql = "SELECT b "
				+ "FROM Exercise a "
				+ "INNER JOIN ExerciseDetail b ON a.id = b.exercise.id "
				+ "WHERE a.id = :idExercise";
		
		return getSession().createQuery(hql).setParameter("idExercise", idExercise).getResultList();
	}
	
}
