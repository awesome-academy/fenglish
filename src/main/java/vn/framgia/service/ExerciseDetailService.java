package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.ExerciseDetailInfo;
import vn.framgia.model.ExerciseDetail;

public interface ExerciseDetailService extends BaseService<Integer, ExerciseDetail> {
	
	List<ExerciseDetailInfo> findExerciseDetails(Integer idExercise);
	
}
