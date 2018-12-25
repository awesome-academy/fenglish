package vn.framgia.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import vn.framgia.bean.ExerciseDetailInfo;
import vn.framgia.model.ExerciseDetail;

public class ExerciseDetailConvertHelper {

	private static final Logger logger = Logger.getLogger(ExerciseConvertHelper.class);

	public static ExerciseDetailInfo convertSingleExDetailToExDetailInfo(ExerciseDetail exerciseDetail) {
		try {
			ExerciseDetailInfo exerciseDetailInfo = new ExerciseDetailInfo();
			BeanUtils.copyProperties(exerciseDetail, exerciseDetailInfo);
			return exerciseDetailInfo;
		} catch (Exception e) {
			logger.error("Error in convertSingleExDetailToExDetailInfo: " + e.getMessage());
			return null;
		}
	}

	public static List<ExerciseDetailInfo> convertExDetailToExDetailInfo(List<ExerciseDetail> exerciseDetails) {
		try {
			List<ExerciseDetailInfo> exerciseDetailInfos = new ArrayList<ExerciseDetailInfo>();

			for (ExerciseDetail exerciseDetail : exerciseDetails) {
				exerciseDetailInfos.add(convertSingleExDetailToExDetailInfo(exerciseDetail));
			}

			return exerciseDetailInfos;
		} catch (Exception e) {
			logger.error("Error in convertExDetailToExDetailInfo: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static ExerciseDetail convertSingleExDetailInfoToExDetail(ExerciseDetailInfo exerciseDetailInfo) {
		try {
			ExerciseDetail exerciseDetail = new ExerciseDetail();
			BeanUtils.copyProperties(exerciseDetailInfo, exerciseDetail);
			return exerciseDetail;
		} catch (Exception e) {
			logger.error("Error in convertSingleExDetailInfoToExDetail: " + e.getMessage());
			return null;
		}
	}

	public static List<ExerciseDetail> convertExDetailInfoToExDetail(List<ExerciseDetailInfo> exerciseDetailInfos) {
		try {
			List<ExerciseDetail> exerciseDetails = new ArrayList<ExerciseDetail>();

			for (ExerciseDetailInfo exerciseDetailInfo : exerciseDetailInfos) {
				exerciseDetails.add(convertSingleExDetailInfoToExDetail(exerciseDetailInfo));
			}

			return exerciseDetails;
		} catch (Exception e) {
			logger.error("Error in convertExDetailInfoToExDetail: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
