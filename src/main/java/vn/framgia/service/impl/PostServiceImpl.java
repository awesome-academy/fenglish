package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.bean.PostInfo;
import vn.framgia.helper.PostConvertHelper;
import vn.framgia.model.Post;
import vn.framgia.service.CategoryService;
import vn.framgia.service.PostService;

public class PostServiceImpl extends BaseServiceImpl implements PostService {

	private static final Logger logger = Logger.getLogger(PostServiceImpl.class);

	@Autowired
	CategoryService categoryService;

	@Override
	public Post findById(Serializable key) {
		try {
			return postDAO.findById(key);
		} catch (Exception e) {
			logger.error("Error in findById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Post saveOrUpdate(Post entity) {
		try {
			Post lockObject = postDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			return postDAO.saveOrUpdate(lockObject);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Post entity) {
		try {
			Post lockObject = postDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			postDAO.delete(lockObject);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Long countListAll() {
		return postDAO.count();
	}

	@Override
	public List<PostInfo> listAll(int pageSize, int pageNumber) {
		try {
			return PostConvertHelper.convertListPostToListPostInfo(postDAO.loadPost(pageSize, pageNumber));
		} catch (Exception e) {
			logger.error("Error in listAll: " + e.getMessage());
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
			logger.error("Error in deletePostById: " + e.getMessage());
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
			logger.error("Error in saveOrUpdatePostInfo: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public PostInfo findPostById(int id) {
		try {
			PostInfo postInfo = PostConvertHelper.convertSinglePostToPostInfo(postDAO.findById(id));
			Hibernate.initialize(postInfo.getCategory());
			return postInfo;
		} catch (Exception e) {
			logger.error("Error in findPostById: " + e.getMessage());
			return null;
		}

	}

	@Override
	public List<PostInfo> loadPostsByCategory(Integer idCategory, Integer page, Integer maxResult) {
		try {
			return PostConvertHelper.convertListPostToListPostInfo(postDAO.findPostsByCategory(idCategory, page, maxResult));
		} catch (Exception e) {
			logger.error("Error in loadPostsByCategory: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public Map<Integer, Long> countPostByCategory() {
		try {
			Map<Integer, Long> counts = new HashMap<Integer, Long>();
			
			for(CategoryInfo category: categoryService.loadAllCategoryExistPost()) {
				counts.put(category.getId(), postDAO.countPostByCategory(category.getId()));
			}
			return counts;
		} catch (Exception e) {
			logger.error("Error in countPostByCategory: " + e.getMessage());
			return Collections.emptyMap();
		}
	}
}
