package vn.framgia.bean;

import javax.validation.constraints.NotBlank;

public class SubjectInfo {
	
	private Integer id;
	@NotBlank
	private String subjectName;
	
	public SubjectInfo() {
	
	}

	public SubjectInfo(Integer id, String subjectName) {
		this.id = id;
		this.subjectName = subjectName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
