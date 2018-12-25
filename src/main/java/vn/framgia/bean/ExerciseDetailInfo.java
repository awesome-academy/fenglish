package vn.framgia.bean;

import vn.framgia.model.Exercise;
import vn.framgia.model.Question;

public class ExerciseDetailInfo {
	
	private Integer id;
	private Exercise exercise;
	private Question question;
	private Integer answer;
	
	public ExerciseDetailInfo() {

	}

	public ExerciseDetailInfo(Integer id, Exercise exercise, Question question, Integer answer) {
		super();
		this.id = id;
		this.exercise = exercise;
		this.question = question;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	
}
