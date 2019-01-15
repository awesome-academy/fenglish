package vn.framgia.controller.client;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.framgia.bean.PostInfo;
import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

	@GetMapping
	public String showPosts(Model model) {
		return "/client/posts";
	}

	@GetMapping("/{id_category}/{page}/{per_page}")
	public @ResponseBody List<PostInfo> loadPostsByCategory(@PathVariable("id_category") Integer idCategory,
			@PathVariable("page") Integer page, @PathVariable("per_page") Integer per_page) {
		return postService.loadPostsByCategory(idCategory, page, per_page);
	}

	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<Integer, Long> countPostByCategory() {
		return postService.countPostByCategory();
	}

	@GetMapping("/{id}")
	public String viewPostDetail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("post", postService.findPostById(id));
		return "/client/post/detail";
	}

}
