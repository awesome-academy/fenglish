package vn.framgia.controller.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.controller.BaseController;

@RestController
@RequestMapping("/categories")
public class CategoriesController extends BaseController {
	
	@GetMapping
	public List<CategoryInfo> loadAllCategory() {
		return categoryService.loadAllCategoryExistPost();
	}
}