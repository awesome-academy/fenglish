package vn.framgia.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.framgia.bean.UserInfo;
import vn.framgia.service.UserService;

@Controller
@RequestMapping("/admin")
public class UsersController {

	private static final int maxResult = 10;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users/page={offset}", method = RequestMethod.GET)
	public String index(@PathVariable("offset") Integer offset, Model model) {

		List<UserInfo> users = userService.loadUsers(offset, maxResult);
		model.addAttribute("count", userService.count());
		model.addAttribute("offset", offset);
		model.addAttribute("users", users);

		return "/users/index";
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, Model model) {

		UserInfo user = userService.findUserById(id);

		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "msg.user.notfound");
		}
		model.addAttribute("user", user);

		return "/users/detail";
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		if (userService.deleteUserById(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "msg.user.deletesuccess");
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("css", "msg.user.deletefail");
		}

		return "redirect:/admin/";
	}

	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {

		UserInfo userForm = userService.findUserById(id);
		model.addAttribute("userForm", userForm);

		return "/users/edit";
	}

	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("userForm") UserInfo userForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "/users/edit";
		}
		userService.saveUserOrUpdate(userForm);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "msg.user.updatesuccess");

		return "redirect:/admin/";
	}
}
