package vn.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class ChatViewController {
	@RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
	public String index() {
		return "/chatview";
	}
}
