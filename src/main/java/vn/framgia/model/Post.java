package vn.framgia.model;
// Generated Dec 7, 2018 9:59:06 AM by Hibernate Tools 5.3.6.Final

import java.util.Date;
import java.util.List;

/**
 * Posts generated by hbm2java
 */
public class Post implements java.io.Serializable {

	private Integer id;
	private Category category;
	private String title;
	private String content;
	private Date createdTime;
	private Integer view;
	private Boolean deleted;
	private List<PostTag> postTags;

	public Post() {
	}

	public Post(Category category, String title, String content, Date createdTime, Integer view, Boolean deleted,
			List<PostTag> postTags) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.view = view;
		this.deleted = deleted;
		this.postTags = postTags;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getView() {
		return this.view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<PostTag> getPostTags() {
		return this.postTags;
	}

	public void setPostTags(List<PostTag> postTags) {
		this.postTags = postTags;
	}

}
