package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Post;

public interface PostDAO extends BaseDAO<Integer, Post> {
	List<Post> loadPost(int pageSize, int pageNumber);

	Long count();

	void deletePost(Integer key);

	Post findPostById(int id);
	
	List<Post> findPostsByCategory(Integer idCategory, Integer page, Integer maxResult);
}
