package vn.framgia.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.QuestionDAO;
import vn.framgia.model.Question;

public class QuestionDAOImpl extends GenericDAO<Integer, Question> implements QuestionDAO {
	@Autowired
	QuestionDAO questionDAO;
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
		return (List<Question>) getSession().createQuery("from Question q").setFirstResult((pageNumber-1)*pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	public Question findQuestionById(int id) {
		// TODO Auto-generated method stub
		return (Question) getSession().createQuery("from Question q where q.id =:id").setParameter("id", id).getSingleResult();
	}
}
