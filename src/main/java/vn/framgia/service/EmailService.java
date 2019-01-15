package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.EmailInfo;

public interface EmailService {
	void sendSimpleMessage(String to, String token);

	void sendListEmailInfo(List<EmailInfo> listEmailInfo);
}
