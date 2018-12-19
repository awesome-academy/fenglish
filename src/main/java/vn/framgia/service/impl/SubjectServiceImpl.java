package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import vn.framgia.bean.SubjectInfo;
import vn.framgia.helper.SubjectConvertHelper;
import vn.framgia.model.Subject;
import vn.framgia.service.SubjectService;

public class SubjectServiceImpl extends BaseServiceImpl implements SubjectService {

	private static final Logger logger = Logger.getLogger(SubjectServiceImpl.class);

	@Override
	public Subject findById(Serializable key) {
		return subjectDAO.findById(key);
	}

	@Override
	public Subject saveOrUpdate(Subject entity) throws IllegalAccessException, InvocationTargetException {
		logger.info("Save Or Update Subject id = " + entity.getId());

		Subject subject = subjectDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);

		try {
			BeanUtils.copyProperties(subject, entity);
			return subjectDAO.saveOrUpdate(subject);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate Subject: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Subject entity) throws IllegalAccessException, InvocationTargetException {
		logger.info("Delete Subject id = " + entity.getId());

		Subject subject = subjectDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);

		try {
			BeanUtils.copyProperties(subject, entity);
			subjectDAO.delete(subject);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete Subject: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<SubjectInfo> loadSubjects(Integer offset, Integer maxResult) {
		return SubjectConvertHelper.convertSubjectToSubjectInfo(subjectDAO.loadSubject(offset, maxResult));
	}

	@Override
	public Long count() {
		return subjectDAO.count();
	}

	@Override
	public boolean deleteSubjectById(Integer id) {
		logger.info("Delete SubjectById id = " + id);

		Subject subject = subjectDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);

		try {
			subjectDAO.delete(subject);
			return true;
		} catch (Exception e) {
			logger.error("Error in deleteSubjectById: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Subject saveOrUpdateSubject(SubjectInfo subjectInfo) {
		logger.info("Save or Update Subject");

		Subject subject = null;

		try {
			// Create new Subject
			if (subjectInfo.getId() == null) {
				subject = new Subject();
				SubjectConvertHelper.convertSingleSubjectInfoToSubject(subject, subjectInfo);
				subject.setDeleted(false);
				return subjectDAO.saveOrUpdate(subject);
			}

			// Update Subject
			subject = subjectDAO.findByIdUsingLock(subjectInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			SubjectConvertHelper.convertSingleSubjectInfoToSubject(subject, subjectInfo);
			return subjectDAO.saveOrUpdate(subject);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdateSubject: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<SubjectInfo> loadAllSubject() {
		try {
			return SubjectConvertHelper.convertSubjectToSubjectInfo(subjectDAO.loadAllSubject());
		} catch (Exception e) {
			logger.error("Error in loadAllSubject: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
