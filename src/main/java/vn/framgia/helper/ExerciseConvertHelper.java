package vn.framgia.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import vn.framgia.bean.ExerciseInfo;
import vn.framgia.model.Exercise;

public class ExerciseConvertHelper {

	private static final Logger logger = Logger.getLogger(ExerciseConvertHelper.class);

	public static Exercise convertSingleExerciseInfoToExercise(ExerciseInfo exerciseInfo, Exercise exercise) {
		try {
			BeanUtils.copyProperties(exerciseInfo, exercise);
			return exercise;
		} catch (Exception e) {
			logger.error("Error in convert ExerciseInfo to Exercise: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unused")
	public static List<Exercise> convertExerciseInfoToExercise(List<ExerciseInfo> exerciseInfos) {
		try {
			List<Exercise> exercises = new ArrayList<Exercise>();

			for (ExerciseInfo exerciseInfo : exerciseInfos) {
				Exercise exercise = new Exercise();
				convertSingleExerciseInfoToExercise(exerciseInfo, exercise);
				exercises.add(exercise);
			}

			return exercises;
		} catch (Exception e) {
			logger.error("Error in convert list ExerciseInfo to list Exercise: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static ExerciseInfo convertSingleExerciseToExerciseInfo(Exercise exercise) {
		try {
			ExerciseInfo exerciseInfo = new ExerciseInfo();
			BeanUtils.copyProperties(exercise, exerciseInfo);
			return exerciseInfo;
		} catch (Exception e) {
			logger.error("Error in convert Exercise to Exercise Info: " + e.getMessage());
			return new ExerciseInfo();
		}
	}

	public static List<ExerciseInfo> convertExerciseToExerciseInfo(List<Exercise> exercises) {
		try {
			List<ExerciseInfo> exerciseInfos = new ArrayList<ExerciseInfo>();

			for (Exercise exercise : exercises) {
				exerciseInfos.add(convertSingleExerciseToExerciseInfo(exercise));
			}

			return exerciseInfos;
		} catch (Exception e) {
			logger.error("Error in convert list Exercise to list ExerciseInfo: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
