package vn.framgia.bean;

import vn.framgia.model.Level;
import vn.framgia.model.Subject;

public class QuestionInfo {
	private Integer id;
	private Level level;
	private Subject subject;
	private String levelName;
	private String subjectName;
	private int levelId;
	private int subjectId;
	private String question;
	private Integer typeQuestion;
	private String mp3Question;
	private String imgQuestion;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private Integer correctAnswer;
	private Boolean deleted;

	public QuestionInfo() {
	}

	public QuestionInfo(Integer id, Level level, Subject subject, String levelName, String subjectName, int levelId,
			int subjectId, String question, Integer typeQuestion, String mp3Question, String imgQuestion,
			String option1, String option2, String option3, String option4, Integer correctAnswer, Boolean deleted) {
		this.id = id;
		this.level = level;
		this.subject = subject;
		this.levelName = levelName;
		this.subjectName = subjectName;
		this.levelId = levelId;
		this.subjectId = subjectId;
		this.question = question;
		this.typeQuestion = typeQuestion;
		this.mp3Question = mp3Question;
		this.imgQuestion = imgQuestion;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
		this.deleted = deleted;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(Integer typeQuestion) {
		this.typeQuestion = typeQuestion;
	}

	public String getMp3Question() {
		return mp3Question;
	}

	public void setMp3Question(String mp3Question) {
		this.mp3Question = mp3Question;
	}

	public String getImgQuestion() {
		return imgQuestion;
	}

	public void setImgQuestion(String imgQuestion) {
		this.imgQuestion = imgQuestion;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
