package vn.framgia.bean;

import java.util.List;

import vn.framgia.model.ExerciseDetail;
import vn.framgia.model.Subject;
import vn.framgia.model.User;

public class ExerciseInfo {
	private Integer id;
	private Subject subject;
	private User user;
	private String exerciseName;
	private String exerciseImg;
	private Integer totalQuestion;
	private List<ExerciseDetail> exerciseDetails;
	
	public ExerciseInfo() {

	}

	public ExerciseInfo(Integer id, Subject subject, User user, String exerciseName, String exerciseImg,
			Integer totalQuestion, List<ExerciseDetail> exerciseDetails) {
		this.id = id;
		this.subject = subject;
		this.user = user;
		this.exerciseName = exerciseName;
		this.exerciseImg = exerciseImg;
		this.totalQuestion = totalQuestion;
		this.exerciseDetails = exerciseDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseImg() {
		return exerciseImg;
	}

	public void setExerciseImg(String exerciseImg) {
		this.exerciseImg = exerciseImg;
	}

	public Integer getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public List<ExerciseDetail> getExerciseDetails() {
		return exerciseDetails;
	}

	public void setExerciseDetails(List<ExerciseDetail> exerciseDetails) {
		this.exerciseDetails = exerciseDetails;
	}
	
}
