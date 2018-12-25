package vn.framgia.service;

import java.util.List;
import java.util.Map;

import vn.framgia.bean.SubjectInfo;
import vn.framgia.model.Subject;

public interface SubjectService extends BaseService<Integer, Subject> {
	
	List<SubjectInfo> loadSubjects(Integer offset, Integer maxResult);
	
	Long count();
	
	boolean deleteSubjectById(Integer id);
	
	Subject saveOrUpdateSubject(SubjectInfo subjectInfo);
	
	List<SubjectInfo> loadAllSubject();
	
	SubjectInfo getSubjectInExercise(Integer idExercise);
	
	Map<Integer, Subject> loadMapSubject();
	
}
