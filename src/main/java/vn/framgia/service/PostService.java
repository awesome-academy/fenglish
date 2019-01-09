package vn.framgia.service;

import java.util.List;
import java.util.Map;

import vn.framgia.bean.PostInfo;
import vn.framgia.model.Post;

public interface PostService extends BaseService<Integer, Post>{
	
	public Long countListAll();

	List<PostInfo> listAll(int pageSize, int pageNumber);
	
	boolean deletePostById(int id);
	
	PostInfo saveOrUpdatePostInfo(PostInfo postInfo);
	
	PostInfo findPostById(int id);
	
	Map<String, List<PostInfo>> loadPosts(Integer page, Integer max);
	
}
