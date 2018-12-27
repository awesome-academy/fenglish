package vn.framgia.bean;

import java.util.Date;
import java.util.List;

import vn.framgia.model.Category;
import vn.framgia.model.PostTag;

public class PostInfo {
	private Integer id;
	private Category category;
	private String title;
	private String content;
	private Date createdTime;
	private Integer view;
	private Boolean deleted;
	private List<PostTag> postTags;

	public PostInfo() {
	}

	public PostInfo(Integer id, Category category, String title, String content, Date createdTime, Integer view,
			Boolean deleted, List<PostTag> postTags) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.view = view;
		this.deleted = deleted;
		this.postTags = postTags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<PostTag> getPostTags() {
		return postTags;
	}

	public void setPostTags(List<PostTag> postTags) {
		this.postTags = postTags;
	}

}
