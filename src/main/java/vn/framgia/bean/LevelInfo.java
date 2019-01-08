package vn.framgia.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.framgia.model.Question;

public class LevelInfo {
	private Integer id;
	private String name;
	private Boolean deleted;
	@JsonIgnore
	private List<Question> questions;

	public LevelInfo() {
	}

	public LevelInfo(Integer id, String name, Boolean deleted, List<Question> questions) {
		this.id = id;
		this.name = name;
		this.deleted = deleted;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
