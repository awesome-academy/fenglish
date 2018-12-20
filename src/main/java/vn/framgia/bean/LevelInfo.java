package vn.framgia.bean;

import java.util.ArrayList;
import java.util.List;

import vn.framgia.model.Question;

public class LevelInfo {
	private Integer id;
	private String name;
	private Boolean deleted;
	private List<Question> questions = new ArrayList<Question>();

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
