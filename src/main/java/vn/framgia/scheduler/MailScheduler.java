package vn.framgia.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {
	@Scheduled(cron = "0 20 1 * * MON-THU")
	public void sendMailWeekly() {

	}
}
