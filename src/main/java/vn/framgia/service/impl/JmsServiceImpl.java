package vn.framgia.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.framgia.bean.EmailInfo;
import vn.framgia.model.User;
import vn.framgia.service.EmailService;
import vn.framgia.service.UserService;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:activemq-config.properties")
public class JmsServiceImpl {
	final private String QUEUE_LIST_MAIL_TO_SEND_INSTANT = "fenglish.queue.list.mail.to.send.instant";
	final private String QUEUE_MAIL_TO_SEND_WEEKLY = "fenglish.queue.mail.to.send.weekly";
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	private ObjectMapper mapper = new ObjectMapper();

	public void sendMessage(String queueName, String message) {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public String receiveMessage(String queueName) {
		Message message = jmsTemplate.receive(queueName);
		TextMessage textMessage = (TextMessage) message;
		try {
			return textMessage.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}

	@JmsListener(destination = QUEUE_LIST_MAIL_TO_SEND_INSTANT)
	public void receiveMailAndSend(String listEmailInfo) {
		try {
			List<EmailInfo> emailInfos = mapper.readValue(listEmailInfo, new TypeReference<List<EmailInfo>>() {
			});
			emailService.sendListEmailInfo(emailInfos);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reciveMailAndSendToAllUser() {
		String emailInfoJson = receiveMessage(QUEUE_MAIL_TO_SEND_WEEKLY);
		try {
			EmailInfo emailInfo = mapper.readValue(emailInfoJson, EmailInfo.class);
			List<EmailInfo> listEmailInfo = convertMailToListMail(emailInfo);
			String listEmailInfoJson = mapper.writeValueAsString(listEmailInfo);
			sendMessage(QUEUE_LIST_MAIL_TO_SEND_INSTANT, listEmailInfoJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// conver a mail to lish mail with email from all user to send
	private List<EmailInfo> convertMailToListMail(EmailInfo emailInfo) {
		List<EmailInfo> listEmailInfo = new ArrayList<>();
		List<User> listUser = userService.findAll();
		for (User user : listUser) {
			listEmailInfo.add(addToToEmailInfo(emailInfo, user.getEmail()));
		}
		return listEmailInfo;
	}

	// Add email for send to emailInfo
	private EmailInfo addToToEmailInfo(EmailInfo emailInfo, String email) {
		EmailInfo emailInfoResult = new EmailInfo();
		emailInfoResult.setTitle(emailInfo.getTitle());
		emailInfoResult.setContent(emailInfo.getContent());
		emailInfoResult.setTo(email);
		return emailInfoResult;
	}
}
