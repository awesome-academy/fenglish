package vn.framgia.multithread;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import vn.framgia.bean.EmailInfo;

@Component
@Scope("prototype")
public class SendMailTask implements Runnable {
	private static final Logger logger = Logger.getLogger(SendMailTask.class);
	EmailInfo emailInfo;

	public SendMailTask() {
	}

	public SendMailTask(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		new EmailServiceImpl().sendEmailInfo(emailInfo);
		logger.info("send email:" + emailInfo.getTo());
	}

}
