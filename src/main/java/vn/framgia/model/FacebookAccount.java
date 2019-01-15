package vn.framgia.model;

import java.io.Serializable;

public class FacebookAccount implements Serializable {

	private Integer userId;
	private User user;
	private String facebookId;

	public FacebookAccount() {

	}

	public FacebookAccount(Integer userId, User user, String facebookId) {
		this.userId = userId;
		this.user = user;
		this.facebookId = facebookId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
