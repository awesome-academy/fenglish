package vn.framgia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import vn.framgia.helper.EmailHelper;
import vn.framgia.service.EmailService;

@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:mailconfig.properties")
public class EmailServiceImpl implements EmailService {
	@Autowired
	public EmailHelper emailHelper;
	
	@Value("${mail.confirm.link}")
	String mailConfirmLink;

	public void sendSimpleMessage(String to, String passwordResetToken) {
		SimpleMailMessage message = new SimpleMailMessage();
		String subject = "Thư xác nhận email đăng ký tài khoản Fenglish";
		String linkConfirm = mailConfirmLink + to+"/"+passwordResetToken;
		String content = "Click this link to confirm your email: "+ linkConfirm;
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		emailHelper.getJavaMailSender().send(message);
	}
}