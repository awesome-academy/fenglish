package vn.framgia.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

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
	public Category saveOrUpdate(Category entity){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Category entity){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CategoryInfo> loadAllCategoryInfo() {
		// TODO Auto-generated method stub
		try {
			return CategoryConvertHelper.convertCategoryToCategoryInfo(categoryDAO.loadAllCategory());
		} catch (Exception e) {
			logger.error("Error in loadAllLevel: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
