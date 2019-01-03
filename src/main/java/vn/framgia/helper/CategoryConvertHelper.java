package vn.framgia.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.model.Category;

public class CategoryConvertHelper {
	private static final Logger logger = Logger.getLogger(CategoryConvertHelper.class);

	public static CategoryInfo convertSingleCategoryToCategoryInfo(Category category) {

		try {
			CategoryInfo categoryInfo = new CategoryInfo();
			BeanUtils.copyProperties(categoryInfo, category);
			return categoryInfo;
		} catch (Exception e) {
			logger.error("Error in convert single category to category Info: " + e.getMessage());
			return null;
		}
	}

	public static List<CategoryInfo> convertCategoryToCategoryInfo(List<Category> Categorys) {

		try {
			List<CategoryInfo> CategoryInfos = new ArrayList<CategoryInfo>();

			for (Category Category : Categorys) {
				CategoryInfo CategoryInfo = convertSingleCategoryToCategoryInfo(Category);
				CategoryInfos.add(CategoryInfo);
			}
			return CategoryInfos;
		} catch (Exception e) {
			logger.error("Error in convert list Category to list Category Info: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static void convertSingleCategoryInfoToCategory(Category Category, CategoryInfo CategoryInfo) {

		try {
			BeanUtils.copyProperties(Category, CategoryInfo);
		} catch (Exception e) {
			logger.error("Error in convert single Category Info to Category:" + e.getMessage());
		}
	}

	public static List<Category> convertCategoryInfoToCategory(List<CategoryInfo> CategoryInfos) {

		try {
			List<Category> Categorys = new ArrayList<Category>();

			for (CategoryInfo CategoryInfo : CategoryInfos) {
				Category Category = new Category();
				convertSingleCategoryInfoToCategory(Category, CategoryInfo);
				Categorys.add(Category);
			}
			return Categorys;
		} catch (Exception e) {
			logger.error("Error in convert list Category Info to list Category: " + e.getMessage());
			return Collections.emptyList();
		}
	}
}
