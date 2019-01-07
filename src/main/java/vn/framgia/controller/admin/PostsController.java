package vn.framgia.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.framgia.bean.CategoryInfo;
import vn.framgia.bean.PostInfo;
import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/admin/posts")
public class PostsController extends BaseController {
	private static final int pageSize = 10;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newPost(Model model) {
		PostInfo postInfo = new PostInfo();
		model.addAttribute("postForm", postInfo);
		List<CategoryInfo> categories = categoryService.loadAllCategoryInfo();
		model.addAttribute("categories", categories);
		return "/posts/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPost(@Valid @ModelAttribute("postForm") PostInfo postForm, HttpServletRequest request,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "/posts/create";
		}

		postService.saveOrUpdatePostInfo(postForm);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "msg.post.createsuccess");
		return "redirect:/admin/posts/page=1";
	}

	@RequestMapping(value = "/page={pageNumber}", method = RequestMethod.GET)
	public String index(@PathVariable("pageNumber") Integer pageNumber, Model model) {
		List<PostInfo> posts = postService.listAll(pageSize, pageNumber);
		model.addAttribute("count", postService.countListAll());
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("posts", posts);

		return "/posts/index";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		if (postService.deletePostById(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "msg.user.deletesuccess");
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("css", "msg.user.deletefail");
		}

		return "redirect:/admin/";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		PostInfo postInfo = postService.findPostById(id);
		List<CategoryInfo> categories = categoryService.loadAllCategoryInfo();
		model.addAttribute("postForm", postInfo);
		model.addAttribute("categories", categories);
		return "/posts/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("postForm") PostInfo postInfo, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "/posts/edit";
		}
		postService.saveOrUpdatePostInfo(postInfo);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "msg.post.updatesuccess");
		return "redirect:/admin/posts/page=1";
	}
}
