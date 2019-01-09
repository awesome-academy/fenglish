package vn.framgia.controller.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.bean.PostInfo;
import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {
	
	private static final int max = 5;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPosts(Model model) {
		return "forward:/posts/page=1";
	}
	
	@RequestMapping(value = "/page={page}", method = RequestMethod.GET)
	public String postsPagination(@PathVariable("page") Integer page, Model model) {
		Map<String, List<PostInfo>> mapPosts = postService.loadPosts(page, max);
		model.addAttribute("mapPosts", mapPosts);
		return "/client/posts";
	}
	
}
