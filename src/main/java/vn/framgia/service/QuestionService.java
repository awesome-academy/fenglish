package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.QuestionInfo;
import vn.framgia.model.Question;

public interface QuestionService extends BaseService<Integer, Question> {
	boolean createQuestion(Question question);

	boolean deleteQuestion(int id);

	Question editQuestion(Question question);

	List<Question> listAll(int pageSize, int pageNumber);

	List<Question> searchBySubjectAndLevel(int subjectId, int levelId);

	public Long countListAll();

	Question findQuestionById(int id);

	List<QuestionInfo> getListQuestionByIdExercise(Integer idExercise);

	QuestionInfo saveOrUpdate(QuestionInfo questionInfo);
	
	boolean saveListQuestion(List<Question> question);
}
