package vn.framgia.controller.client;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			session.setAttribute("user", userInfo);

			return "/client/user-info";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editProfile() {
		return "/client/edit-profile";
	}

}
