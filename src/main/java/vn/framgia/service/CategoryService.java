package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.model.Category;

public interface CategoryService extends BaseService<Integer, Category> {

	List<CategoryInfo> loadAllCategoryInfo();
	
	List<CategoryInfo> loadAllCategoryExistPost();

}
