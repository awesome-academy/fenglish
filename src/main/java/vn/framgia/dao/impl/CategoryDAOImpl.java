package vn.framgia.dao.impl;

import java.util.List;

import vn.framgia.dao.CategoryDAO;
import vn.framgia.dao.GenericDAO;
import vn.framgia.model.Category;

public class CategoryDAOImpl extends GenericDAO<Integer, Category> implements CategoryDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> loadAllCategory() {
		String hql = "FROM Category a WHERE a.status = 0";
		return getSession().createQuery(hql).getResultList();
	}

	@Override
	public List<Category> loadCategoryExistPost() {
		String hql = "SELECT a "
				+ "FROM Category a "
				+ "INNER JOIN Post b ON a.id = b.category.id "
				+ "GROUP BY a.id "
				+ "HAVING COUNT(b.id) > 0";
		return getSession().createQuery(hql, Category.class).getResultList();
	}

}
