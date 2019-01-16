package vn.framgia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import vn.framgia.bean.EmailInfo;
import vn.framgia.helper.EmailHelper;
import vn.framgia.service.EmailService;

@Component
@Scope("prototype")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:mailconfig.properties")
public class EmailServiceImpl implements EmailService {
	@Autowired
	public EmailHelper emailHelper;
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	@Value("${mail.confirm.link}")
	String mailConfirmLink;

	public void sendSimpleMessage(String to, String passwordResetToken) {
		SimpleMailMessage message = new SimpleMailMessage();
		String subject = "Thư xác nhận email đăng ký tài khoản Fenglish";
		String linkConfirm = mailConfirmLink + to + "/" + passwordResetToken;
		String content = "Click this link to confirm your email: " + linkConfirm;
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		emailHelper.getJavaMailSender().send(message);
	}

	public void sendListEmailInfo(List<EmailInfo> listEmailInfo) {
		for (EmailInfo emailInfo : listEmailInfo) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailInfo.getTo());
			message.setSubject(emailInfo.getTitle());
			message.setText(emailInfo.getContent());
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					emailHelper.getJavaMailSender().send(message);
				}
			});
		}
	}

	public void sendMail(String to, String passwordResetToken, EmailInfo emailInfo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(emailInfo.getTitle());
		message.setText(emailInfo.getContent());
		emailHelper.getJavaMailSender().send(message);
	}
}