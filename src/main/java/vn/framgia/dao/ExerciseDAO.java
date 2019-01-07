package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Exercise;

public interface ExerciseDAO extends BaseDAO<Integer, Exercise> {

	List<Exercise> findListExerciseByIdUser(Integer idUser);

	Long countExerciseByMonthAndYear(int month, int year);
}
