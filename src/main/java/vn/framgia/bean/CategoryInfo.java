package vn.framgia.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.framgia.model.Post;

public class CategoryInfo {
	private Integer id;
	private String categoryName;
	private Boolean status;
	@JsonIgnore
	private List<Post> posts;

	public CategoryInfo() {
	}

	public CategoryInfo(String categoryName, Boolean status, List<Post> posts) {
		this.categoryName = categoryName;
		this.status = status;
		this.posts = posts;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
