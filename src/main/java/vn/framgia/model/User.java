package vn.framgia.model;
// Generated Dec 7, 2018 9:59:06 AM by Hibernate Tools 5.3.6.Final

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Users generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer id;
	private String passwordHash;
	private String passwordResetToken;
	private String email;
	private String avatar;
	private String phone;
	private Boolean gender;
	private Timestamp birthday;
	private String role;
	private String fullname;
	private boolean deleted;
	private GoogleAccount googleAccount;
	private List<Exercise> exercises = new ArrayList<Exercise>();

	public User() {
	}

	public User(String email, boolean deleted) {
		this.email = email;
		this.deleted = deleted;
	}

	public User(String passwordHash, String passwordResetToken, String email, String avatar, String phone,
			Boolean gender, Timestamp birthday, String role, String fullname, boolean deleted, GoogleAccount googleAccount,
			List<Exercise> exercises) {
		this.passwordHash = passwordHash;
		this.passwordResetToken = passwordResetToken;
		this.email = email;
		this.avatar = avatar;
		this.phone = phone;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.fullname = fullname;
		this.deleted = deleted;
		this.googleAccount = googleAccount;
		this.exercises = exercises;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordResetToken() {
		return this.passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public GoogleAccount getGoogleAccount() {
		return this.googleAccount;
	}

	public void setGoogleAccount(GoogleAccount googleAccount) {
		this.googleAccount = googleAccount;
	}

	public List<Exercise> getExercises() {
		return this.exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	@Transient
	  public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.role));
	    return authorities;
	  }

}
