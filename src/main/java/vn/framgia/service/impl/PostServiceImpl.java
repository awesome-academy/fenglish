package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.bean.PostInfo;
import vn.framgia.helper.PostConvertHelper;
import vn.framgia.model.Post;
import vn.framgia.service.CategoryService;
import vn.framgia.service.PostService;

public class PostServiceImpl extends BaseServiceImpl implements PostService {
	@Autowired
	CategoryService categoryService;
	@Override
	public Post findById(Serializable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post saveOrUpdate(Post entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Post entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long countListAll() {
		// TODO Auto-generated method stub
		return postDAO.count();
	}

	@Override
	public List<Post> listAll(int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		try {
			return postDAO.loadPost(pageSize, pageNumber);
		} catch (Exception e) {
			// TODO: handle exception
			return Collections.emptyList();
		}
	}

	@Override
	public boolean deletePostById(int id) {
		try {
			Post lockEntity = postDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);
			postDAO.delete(lockEntity);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public PostInfo saveOrUpdatePostInfo(PostInfo postInfo) {
		try {
			if (postInfo.getId() == null) {
				Post post = new Post();
				PostConvertHelper.copyValuePostInfoToPost(post, postInfo);
				post.setCategory(categoryService.findById(postInfo.getCategoryId()));
				post.setDeleted(false);
				post.setCreatedTime(new Date());
				return PostConvertHelper.convertSinglePostToPostInfo(postDAO.saveOrUpdate(post));
			}
			Post post = postDAO.findByIdUsingLock(postInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			PostConvertHelper.copyValuePostInfoToPost(post, postInfo);
			post.setCategory(categoryService.findById(postInfo.getCategoryId()));
			return PostConvertHelper.convertSinglePostToPostInfo(postDAO.saveOrUpdate(post));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Post findPostById(int id) {
		try {
			Post post = postDAO.findPostById(id);
			Hibernate.initialize(post.getCategory());
			return post;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
