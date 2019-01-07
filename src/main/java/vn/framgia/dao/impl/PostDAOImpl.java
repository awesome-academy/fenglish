package vn.framgia.dao.impl;

import java.util.List;

import vn.framgia.dao.GenericDAO;
import vn.framgia.dao.PostDAO;
import vn.framgia.model.Post;

public class PostDAOImpl extends GenericDAO<Integer, Post> implements PostDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> loadPost(int pageSize, int pageNumber) {
		return (List<Post>) getSession().createQuery("from Post p where p.deleted = 0")
				.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	public Long count() {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM Post a WHERE a.deleted = 0").getSingleResult();
	}

	@Override
	public void deletePost(Integer key) {
		String hql = "UPDATE Post a SET a.deleted = 1 WHERE a.id = :id";
		getSession().createQuery(hql).setParameter("id", key).executeUpdate();
	}

	@Override
	public Post findPostById(int id) {
		return (Post) getSession().createQuery("from Post q where q.id =:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Post> findPostsByCategory(Integer idCategory, Integer page, Integer maxResult) {
		String hql = "SELECT a "
				+ "FROM Post a "
				+ "INNER JOIN Category b ON a.category.id = b.id "
				+ "WHERE b.id = :id";
		return getSession().createQuery(hql, Post.class).setParameter("id", idCategory)
				.setFirstResult((page - 1) * maxResult).setMaxResults(maxResult)
				.getResultList();
	}
}
