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
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

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
		return (List<Question>) getSession().createQuery("from Question q where q.deleted = 0").setFirstResult(pageNumber*pageSize)
				.setMaxResults(pageSize).getResultList();
	}
}
