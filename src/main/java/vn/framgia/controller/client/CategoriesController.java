package vn.framgia.controller.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.controller.BaseController;

@RestController
@RequestMapping("/categories")
public class CategoriesController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<CategoryInfo> loadAllCategory() {
		return categoryService.loadAllCategoryExistPost();
	}
}