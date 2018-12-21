package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.ExerciseDetail;

public interface ExerciseDetailDAO extends BaseDAO<Integer, ExerciseDetail> {
	
	List<ExerciseDetail> findExerciseDetails(Integer idExercise);
	
}
