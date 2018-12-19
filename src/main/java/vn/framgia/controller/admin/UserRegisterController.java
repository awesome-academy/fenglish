package vn.framgia.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.framgia.bean.UserInfo;
import vn.framgia.helper.EmailHelper;
import vn.framgia.helper.UserConvertHelper;
import vn.framgia.model.User;
import vn.framgia.service.EmailService;
import vn.framgia.service.UserService;

@Controller
public class UserRegisterController {
	@Autowired
	EmailService mailService;
	@Autowired
	EmailHelper emailHelper;
	@Autowired
	UserService userService;

	@RequestMapping("/register")
	public String register(final Model model) {
		return "/client/register";
	}

	@PostMapping(path = "/register")
	public String addUser(@ModelAttribute("userInfo") UserInfo userInfo, HttpSession httpSession,
			final RedirectAttributes redirectAttributes) {
		String passwordResetToken = emailHelper.getPasswordResetToken();
		User user;
		user = UserConvertHelper.convertUserInfoForRegister(userInfo);		
		if (userService.saveUserAfferRegister(user, passwordResetToken)) {
			mailService.sendSimpleMessage(userInfo.getEmail(), passwordResetToken);
			return "/client/register-confirm";
		} else {
			return "redirect:/register";
		}
	}

	@GetMapping(path = "/register/confirm/{email}/{token}")
	public String confirmUser(@PathVariable String email, @PathVariable String token) {
		if(userService.confirmRegister(email, token)) {
			return "/login";
		};
		return "redirect:/login";
	}
}
