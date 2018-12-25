package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import vn.framgia.bean.ExerciseDetailInfo;
import vn.framgia.helper.ExerciseDetailConvertHelper;
import vn.framgia.model.ExerciseDetail;
import vn.framgia.service.ExerciseDetailService;

public class ExerciseDetailServiceImpl extends BaseServiceImpl implements ExerciseDetailService {

	private static final Logger logger = Logger.getLogger(ExerciseDetailServiceImpl.class);

	@Override
	public ExerciseDetail findById(Serializable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExerciseDetail saveOrUpdate(ExerciseDetail entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ExerciseDetail entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ExerciseDetailInfo> findExerciseDetails(Integer idExercise) {
		try {
			return ExerciseDetailConvertHelper
					.convertExDetailToExDetailInfo(exerciseDetailDAO.findExerciseDetails(idExercise));
		} catch (Exception e) {
			logger.error("Error in findExerciseDetails: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
