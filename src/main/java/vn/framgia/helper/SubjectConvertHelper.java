package vn.framgia.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.SubjectInfo;
import vn.framgia.model.Subject;

public class SubjectConvertHelper {

	private static final Logger logger = Logger.getLogger(SubjectConvertHelper.class);

	public static SubjectInfo convertSingleSubjectToSubjectInfo(Subject subject) {

		try {
			SubjectInfo subjectInfo = new SubjectInfo();
			BeanUtils.copyProperties(subjectInfo, subject);
			return subjectInfo;
		} catch (Exception e) {
			logger.error("Error in convert single Subject to Subject Info: " + e.getMessage());
			return null;
		}
	}

	public static List<SubjectInfo> convertSubjectToSubjectInfo(List<Subject> subjects) {

		try {
			List<SubjectInfo> subjectInfos = new ArrayList<SubjectInfo>();

			for (Subject subject : subjects) {
				SubjectInfo subjectInfo = convertSingleSubjectToSubjectInfo(subject);
				subjectInfos.add(subjectInfo);
			}
			return subjectInfos;
		} catch (Exception e) {
			logger.error("Error in convert list Subject to list Subject Info: " + e.getMessage());
			return new ArrayList<SubjectInfo>();
		}
	}

	public static void convertSingleSubjectInfoToSubject(Subject subject, SubjectInfo subjectInfo) {

		try {
			BeanUtils.copyProperties(subject, subjectInfo);
		} catch (Exception e) {
			logger.error("Error in convert single Subject Info to Subject:" + e.getMessage());
		}
	}

	public static List<Subject> convertSubjectInfoToSubject(List<SubjectInfo> subjectInfos) {

		try {
			List<Subject> subjects = new ArrayList<Subject>();
			
			for (SubjectInfo subjectInfo : subjectInfos) {
				Subject subject = new Subject();
				convertSingleSubjectInfoToSubject(subject, subjectInfo);
				subjects.add(subject);
			}
			return subjects;
		} catch (Exception e) {
			logger.error("Error in convert list Subject Info to list Subject: " + e.getMessage());
			return new ArrayList<Subject>();
		}
	}

}
