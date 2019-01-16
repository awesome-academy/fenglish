package vn.framgia.multithread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import vn.framgia.service.impl.JmsServiceImpl;

@Component
public class MailScheduler {
	@Autowired
	JmsServiceImpl jmsServiceImpl;

	@Scheduled(cron = "0 20 1 * * MON-THU")
	public void sendMailWeekly() {
		jmsServiceImpl.reciveMailAndSendToAllUser();
	}
}
