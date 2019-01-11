package vn.framgia.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.framgia.service.impl.JmsServiceImpl;

@Controller
@RequestMapping("/admin/mail")
public class MailController {
	@Autowired
	JmsServiceImpl jmsServiceImpl;
	@RequestMapping(value = { "/sendmail" }, method = RequestMethod.GET)
	public String index() {
		jmsServiceImpl.sendMessage("fenglish.fenglish1", "123");
		jmsServiceImpl.receiveMessage("fenglish.fenglish");
		return "/mail/mailSentWeekly";
	}
}
