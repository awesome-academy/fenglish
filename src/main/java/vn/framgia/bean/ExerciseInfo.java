package vn.framgia.bean;

import java.sql.Timestamp;
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
	private Timestamp createTime;
	private Boolean submitted;
	private Boolean deleted;
	private List<ExerciseDetail> exerciseDetails;
	
	public ExerciseInfo() {

	}

	public ExerciseInfo(Subject subject, User user, String exerciseName, String exerciseImg, Integer totalQuestion,
			Timestamp createTime, Boolean submitted, Boolean deleted, List<ExerciseDetail> exerciseDetails) {
		this.subject = subject;
		this.user = user;
		this.exerciseName = exerciseName;
		this.exerciseImg = exerciseImg;
		this.totalQuestion = totalQuestion;
		this.createTime = createTime;
		this.submitted = submitted;
		this.deleted = deleted;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
