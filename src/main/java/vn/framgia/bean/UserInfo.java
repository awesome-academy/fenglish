package vn.framgia.bean;


import vn.framgia.model.GoogleAccount;

public class UserInfo {
	private Integer id;
	private String passwordHash;
	private String passwordResetToken;
	private String email;
	private String avatar;
	private String phone;
	private String gender;
	private String birthday;
	private String role;
	private String fullname;
	private GoogleAccount googleAccount;

	public UserInfo() {

	}

	public UserInfo(Integer id, String passwordHash, String passwordResetToken, String email, String avatar,
			String phone, String gender, String birthday, String role, String fullname, GoogleAccount googleAccount) {
		super();
		this.id = id;
		this.passwordHash = passwordHash;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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