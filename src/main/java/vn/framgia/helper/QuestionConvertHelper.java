package vn.framgia.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.QuestionInfo;
import vn.framgia.model.Question;

public class QuestionConvertHelper {
	private static final Logger logger = Logger.getLogger(QuestionConvertHelper.class);
	public static QuestionInfo convertQuestionToQuestionInfo(Question question) {
		QuestionInfo questionInfo = new QuestionInfo();

		try {
			BeanUtils.copyProperties(questionInfo, question);
			questionInfo.setLevelName(question.getLevel().getName());
			questionInfo.setSubjectName(question.getSubject().getSubjectName());
		} catch (Exception e) {
			logger.error("Error in convertQuestionInfo: " + e.getMessage());
		}

		return questionInfo;
	}

	public static Question convertQuestionInfoToQuestion(QuestionInfo questionInfo) {
		Question question = new Question();

		try {
			BeanUtils.copyProperties(question, questionInfo);
		} catch (Exception e) {
			logger.error("Error in convertQuestionInfo: " + e.getMessage());
		}

		return question;
	}
	
	public static void copyValueQuestionInfoToQuestion(Question question, QuestionInfo questionInfo) {
		try {
			BeanUtils.copyProperties(question, questionInfo);
		} catch (Exception e) {
			logger.error("Error in convertQuestionInfo: " + e.getMessage());
		}
	}
	
	public static List<QuestionInfo> convertListQuestionToListQuestionInfo(List<Question> listQuestion) {

		List<QuestionInfo> listQuestionInfo = new ArrayList<QuestionInfo>();

		for (Question question : listQuestion) {
			listQuestionInfo.add(convertQuestionToQuestionInfo(question));
		}
		return listQuestionInfo;
	}
	public static List<Question> convertListQuestionInfoToListQuestion(List<QuestionInfo> listQuestionInfo) {

		List<Question> listQuestion = new ArrayList<Question>();

		for (QuestionInfo questionInfo : listQuestionInfo) {
			listQuestion.add(convertQuestionInfoToQuestion(questionInfo));
		}
		return listQuestion;
	}
}
