package vn.framgia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.QuestionDAO;
import vn.framgia.model.Level;
import vn.framgia.model.Question;
import vn.framgia.model.Subject;

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

	@Override
	public List<Question> searchQuestions(String name, Integer idSubject, Integer idLevel) {
		return getSession().createQuery(getCriteria(name, idSubject, idLevel)).getResultList();
	}

	private CriteriaQuery<Question> getCriteria(String name, Integer idSubject, Integer idLevel) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Question> cq = builder.createQuery(Question.class);
		Root<Question> root = cq.from(Question.class);

		Join<Question, Subject> subjectJoin = root.join("subject", JoinType.INNER);
		Join<Question, Level> levelJoin = root.join("level", JoinType.INNER);

		cq.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotBlank(name)) {
			predicates.add(builder.like(root.get("question"), "%" + name + "%"));
		}

		if (idSubject != null) {
			predicates.add(builder.equal(subjectJoin.get("id"), idSubject));
		}

		if (idLevel != null) {
			predicates.add(builder.equal(levelJoin.get("id"), idLevel));
		}

		cq.where(predicates.stream().toArray(Predicate[]::new));
		return cq;
	}
}
