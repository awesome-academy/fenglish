package vn.framgia.model;
// Generated Dec 7, 2018 9:59:06 AM by Hibernate Tools 5.3.6.Final

import java.util.ArrayList;
import java.util.List;

/**
 * Tags generated by hbm2java
 */
public class Tag implements java.io.Serializable {

	private Integer id;
	private String tagName;
	private String status;
	private List<PostTag> postTags = new ArrayList<PostTag>();

	public Tag() {
	}

	public Tag(String tagName, String status, List<PostTag> postTags) {
		this.tagName = tagName;
		this.status = status;
		this.postTags = postTags;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PostTag> getPostTags() {
		return this.postTags;
	}

	public void setPostTags(List<PostTag> postTags) {
		this.postTags = postTags;
	}

}
