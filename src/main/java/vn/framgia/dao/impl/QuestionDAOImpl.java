package vn.framgia.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.QuestionDAO;
import vn.framgia.model.Question;

public class QuestionDAOImpl extends GenericDAO<Integer, Question> implements QuestionDAO {
	private static final Logger logger = Logger.getLogger(QuestionDAOImpl.class);

	public QuestionDAOImpl() {
		super(Question.class);
	}

	public QuestionDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	public Long countListAll() {
		String countQ = "Select count (a.id) from Question a";
		return (Long) getSession().createQuery(countQ).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listAll(int pageSize, int pageNumber) {
		logger.info("Get list question");
		return (List<Question>) getSession().createQuery("from Question q").setFirstResult((pageNumber - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	public Question findQuestionById(int id) {
		return (Question) getSession().createQuery("from Question q where q.id =:id").setParameter("id", id)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionByIdSubject(Integer idSubject) {
		String hql = "FROM Question a WHERE a.deleted = 0 AND a.subject.id = :idSubject";
		return getSession().createQuery(hql).setParameter("idSubject", idSubject).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionByIdExercise(Integer idExercise) {
		String hql = "SELECT a FROM Question a " + "INNER JOIN ExerciseDetail b ON a.id = b.question.id "
				+ "WHERE a.deleted = 0 AND b.exercise.id = :idExercise";
		return getSession().createQuery(hql).setParameter("idExercise", idExercise).getResultList();
	}

	@Override
	public boolean saveListQuestion(List<Question> listQuestion) {
		int batchSize = 30;
		int totalRecords = listQuestion.size();
		for (int i = 0; i < totalRecords; i++) {
			Question question = listQuestion.get(i);
			getSession().saveOrUpdate(question);
			if (i % batchSize == 0 && i > 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		return true;
	}
}
