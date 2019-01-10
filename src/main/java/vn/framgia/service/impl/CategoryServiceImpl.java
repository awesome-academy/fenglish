package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.helper.CategoryConvertHelper;
import vn.framgia.model.Category;
import vn.framgia.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

	@Override
	public Category findById(Serializable key) {
		return categoryDAO.findById(key);
	}

	@Override
	public Category saveOrUpdate(Category entity) {
		try {
			Category lockObject = categoryDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			return categoryDAO.saveOrUpdate(lockObject);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Category entity) {
		try {
			Category lockObject = categoryDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			categoryDAO.delete(lockObject);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<CategoryInfo> loadAllCategoryInfo() {
		try {
			List<CategoryInfo> categories = CategoryConvertHelper.convertCategoryToCategoryInfo(categoryDAO.loadAllCategory());
			return categories;
		} catch (Exception e) {
			logger.error("Error in loadAllCategory: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<CategoryInfo> loadAllCategoryExistPost() {
		try {
			return CategoryConvertHelper.convertCategoryToCategoryInfo(categoryDAO.loadCategoryExistPost());
		} catch (Exception e) {
			logger.error("Error in loadAllCategoryExistPost: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
