package vn.framgia.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.bean.UserInfo;
import vn.framgia.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/listUsers/{offset}", method = RequestMethod.GET)
	public String home(Model model, @PathVariable(value = "offset", required = false) Integer offset) {
		
		List<UserInfo> listUser = userService.loadUsers(offset, 10);
		model.addAttribute("count", userService.count());
		model.addAttribute("offset", offset);
		model.addAttribute("listUsers", listUser);
		return "listUser";
	}
}
