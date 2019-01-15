package vn.framgia.service;

import java.util.List;

import vn.framgia.bean.EmailInfo;

public interface JmsService {
	void sendListMailToJms(List<EmailInfo> listEmailWeekly);
}
