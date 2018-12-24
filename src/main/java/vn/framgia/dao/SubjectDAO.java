package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Subject;

public interface SubjectDAO extends BaseDAO<Integer, Subject> {
	
	List<Subject> loadSubject(Integer offset, Integer maxResult);
	
	Long count();
	
	void deleteSubject(Integer key);
	
	List<Subject> loadAllSubject();
	
	Subject getSubjectInExercise(Integer idExercise);
	
}
