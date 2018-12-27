package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.PostInfo;
import vn.framgia.model.Post;

public interface PostService extends BaseService<Integer, Post>{
	public Long countListAll();
	List<Post> listAll(int pageSize, int pageNumber);
	boolean deletePostById(int id);
	PostInfo saveOrUpdatePostInfo(PostInfo postInfo);
	Post findPostById(int id);
}
