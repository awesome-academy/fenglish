package vn.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String index() {
		return "forward:/admin/users/page=1/";
	}
	
	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String listSubject() {
		return "forward:/admin/subjects/page=1/";
	}
}
