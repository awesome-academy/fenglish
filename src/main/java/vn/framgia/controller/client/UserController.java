package vn.framgia.controller.client;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.framgia.bean.UserInfo;
import vn.framgia.controller.BaseController;
import vn.framgia.helper.UserConvertHelper;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String userInfo(Authentication authentication, HttpSession session) {

		if (authentication != null) {
			String authName = authentication.getName();
			UserInfo userInfo = UserConvertHelper.convertSingleUserToUserInfo(userService.findByEmail(authName));
			session.setAttribute("current_user", userInfo);

			return "/client/user-info";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editProfile(Model model, HttpSession session) {

		UserInfo user = (UserInfo) session.getAttribute("current_user");

		if (user != null) {
			model.addAttribute("userForm", user);
			return "/client/edit-profile";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("userForm") UserInfo userForm, BindingResult bindingResult,
			@RequestParam("imgAvatar") MultipartFile multipart, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "/client/edit-profile";
		}

		if (multipart != null & multipart.getSize() != 0) {
			userForm = userService.updateUserAndChangeAvatar(userForm, multipart);
		} else {
			userForm = userService.saveUserOrUpdate(userForm);
		}
		session.setAttribute("current_user", userForm);

		return "redirect:/users/show";
	}
}
