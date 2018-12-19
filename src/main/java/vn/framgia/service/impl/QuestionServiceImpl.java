package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.dao.QuestionDAO;
import vn.framgia.model.Question;
import vn.framgia.service.QuestionService;

public class QuestionServiceImpl extends BaseServiceImpl implements QuestionService {
	@Autowired
	QuestionDAO questionDAO;

	@Override
	public boolean createQuestion(Question question) {
		questionDAO.saveOrUpdate(question);
		return false;
	}

	@Override
	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		try {
			Question lockEntity = questionDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);
			questionDAO.delete(lockEntity);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	@Override
	public Question editQuestion(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> searchBySubjectAndLevel(int subjectId, int levelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question findById(Serializable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question saveOrUpdate(Question entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Question entity) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long countListAll() {
		// TODO Auto-generated method stub
		return questionDAO.countListAll();
	}

	@Override
	public List<Question> listAll(int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return questionDAO.listAll(pageSize, pageNumber);
	}

	@Override
	public Question findQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionDAO.findQuestionById(id);
	}

}
