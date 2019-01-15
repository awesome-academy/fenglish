package vn.framgia.service.impl;

import java.io.IOException;

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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.framgia.bean.EmailInfo;
import vn.framgia.service.EmailService;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:activemq-config.properties")
public class JmsServiceImpl {
	final private String QUEUE_MAIL_TO_SEND_INSTANT = "fenglish.queue.mail.to.send.instant";
	@Autowired
	private JmsTemplate jmsTemplate;
	private ObjectMapper mapper = new ObjectMapper();
	private EmailService emailService;

	public void sendMessage(String queueName, String message) {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public void receiveMessage(String queueName) {
		Message message = jmsTemplate.receive(queueName);
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			System.out.println("received: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@JmsListener(destination = QUEUE_MAIL_TO_SEND_INSTANT)
	public void receiveMailAndSend(String emailInfo) {
		try {
			EmailInfo email = mapper.readValue(emailInfo, EmailInfo.class);
			emailService.sendEmailInfo(email);
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
}
