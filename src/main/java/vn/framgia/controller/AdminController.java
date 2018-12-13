package vn.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String index() {
		return "forward:/admin/users/1/";
	}
}