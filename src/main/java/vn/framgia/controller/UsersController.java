package vn.framgia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.bean.UserInfo;
import vn.framgia.service.UserService;

@Controller
@RequestMapping("/admin")
public class UsersController {
	
	private static final int maxResult = 10;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users/{offset}", method = RequestMethod.GET)
	public String index(@PathVariable("offset") Integer offset, Model model) {
		
		List<UserInfo> users = userService.loadUsers(offset, maxResult);
		model.addAttribute("count", userService.count());
		model.addAttribute("offset", offset);
		model.addAttribute("users", users);
		
		return "/users/index";
	}
	
}
