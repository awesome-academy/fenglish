package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Post;
import vn.framgia.model.Question;

public interface PostDAO extends BaseDAO<Integer, Post> {
	List<Post> loadPost(int pageSize, int pageNumber);

	Long count();

	void deletePost(Integer key);
	
	Post findPostById(int id);
}
