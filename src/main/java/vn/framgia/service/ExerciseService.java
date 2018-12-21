package vn.framgia.service;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.model.Exercise;
import vn.framgia.model.Subject;

public interface ExerciseService extends BaseService<Integer, Exercise> {
	
	ExerciseInfo createExercise(String authName, Subject subject, Integer totalQuestion);
	
	boolean checkUserAuthentication(Integer id, String authName);
	
	ExerciseInfo findExerciseById(Integer id);
	
}
