package vn.framgia.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import vn.framgia.model.GoogleAccount;
import vn.framgia.validator.PhoneConstraint;

public class UserInfo {
	private Integer id;
	private String passwordHash;
	private String passwordResetToken;	
	private String password;
	private String avatar;
	private String gender;
	private String birthday;
	private String role;
	private GoogleAccount googleAccount;
	
	@Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String email;
	
	@NotBlank
	private String fullname;
	
	@PhoneConstraint
	private String phone;
	
	public UserInfo() {

	}

	public UserInfo(Integer id, String password, String passwordResetToken, String email, String avatar, String phone,
			String gender, String birthday, String role, String fullname, GoogleAccount googleAccount) {
		this.id = id;
		this.password = password;
		this.passwordResetToken = passwordResetToken;
		this.email = email;
		this.avatar = avatar;
		this.phone = phone;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.fullname = fullname;
		this.googleAccount = googleAccount;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public GoogleAccount getGoogleAccount() {
		return googleAccount;
	}

	public void setGoogleAccount(GoogleAccount googleAccount) {
		this.googleAccount = googleAccount;
	}

}