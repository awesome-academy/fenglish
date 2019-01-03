package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.beans.BeanUtils;

import vn.framgia.bean.ExerciseDetailInfo;
import vn.framgia.helper.ExerciseDetailConvertHelper;
import vn.framgia.model.ExerciseDetail;
import vn.framgia.service.ExerciseDetailService;

public class ExerciseDetailServiceImpl extends BaseServiceImpl implements ExerciseDetailService {

	private static final Logger logger = Logger.getLogger(ExerciseDetailServiceImpl.class);

	@Override
	public ExerciseDetail findById(Serializable key) {
		try {
			return exerciseDetailDAO.findById(key);
		} catch (Exception e) {
			logger.error("Error in findById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public ExerciseDetail saveOrUpdate(ExerciseDetail entity) {
		try {
			ExerciseDetail exerciseDetail = exerciseDetailDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, exerciseDetail);
			return exerciseDetailDAO.saveOrUpdate(exerciseDetail);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(ExerciseDetail entity) {
		try {
			ExerciseDetail exerciseDetail = exerciseDetailDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(entity, exerciseDetail);
			exerciseDetailDAO.delete(exerciseDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
			throw e;
		}
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
