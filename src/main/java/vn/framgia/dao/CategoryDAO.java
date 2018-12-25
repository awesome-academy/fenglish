package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	List<Category> loadAllCategory();
}
