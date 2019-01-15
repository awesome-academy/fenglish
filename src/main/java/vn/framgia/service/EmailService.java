package vn.framgia.service;

import vn.framgia.bean.EmailInfo;

public interface EmailService {
	void sendSimpleMessage(String to, String token);

	void sendEmailInfo(EmailInfo emailInfo);
}
