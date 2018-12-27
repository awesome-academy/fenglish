package vn.framgia.controller.admin;

import java.util.List;

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

import vn.framgia.bean.PostInfo;
import vn.framgia.controller.BaseController;
import vn.framgia.helper.PostConvertHelper;

@Controller
@RequestMapping("/admin/posts")
public class PostsController extends BaseController {
	private static final int pageSize = 10;

	@RequestMapping(value = "/posts/create", method = RequestMethod.GET)
	public String create() {
		return "/create";
	}

	@RequestMapping(value = "/page={pageNumber}", method = RequestMethod.GET)
	public String index(@PathVariable("pageNumber") Integer pageNumber, Model model) {

		List<PostInfo> listPostInfo = PostConvertHelper
				.convertListPostToListPostInfo(postService.listAll(pageSize, pageNumber));
		model.addAttribute("count", postService.countListAll());
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("posts", listPostInfo);

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
		PostInfo postInfo = PostConvertHelper.convertSinglePostToPostInfo(postService.findPostById(id));
		model.addAttribute("postForm", postInfo);
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
		redirectAttributes.addFlashAttribute("msg", "msg.user.updatesuccess");
		return "redirect:/admin/posts/page=1";
	}
}
