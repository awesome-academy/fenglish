package vn.framgia.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.framgia.bean.EmailInfo;
import vn.framgia.service.impl.JmsServiceImpl;

@Controller
@RequestMapping("/admin/mail")
public class MailController {
	@Autowired
	JmsServiceImpl jmsServiceImpl;
	final private String QUEUE_MAIL_TO_SEND_WEEKLY = "fenglish.queue.mail.to.send.weekly";
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(Model model) throws JsonProcessingException {
		model.addAttribute("emailForm", new EmailInfo());
		return "/mail/mailSentWeekly";
	}

	@RequestMapping(value = { "/sendMail" }, method = RequestMethod.POST)
	public String sendMailAllUser(@Valid @ModelAttribute("emailForm") EmailInfo emailInfo, BindingResult bindingResult, RedirectAttributes redirectAttributes)
			throws JsonProcessingException {
		if (bindingResult.hasErrors()) {
			return "/mail/mailSentWeekly";
		}
		jmsServiceImpl.sendMessage(QUEUE_MAIL_TO_SEND_WEEKLY, mapper.writeValueAsString(emailInfo));
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("css", "title.admin.mail.succesadd");
		return "redirect:/admin/mail/";
	}
}
