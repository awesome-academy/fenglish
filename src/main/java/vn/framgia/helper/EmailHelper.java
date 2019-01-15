package vn.framgia.helper;

import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:mailconfig.properties")
public class EmailHelper {

	@Value("${spring.mail.host}")
	String springMailHost;
	@Value("${spring.mail.port}")
	int springMailPort;
	@Value("${spring.mail.username}")
	String springMailUserName;
	@Value("${spring.mail.password}")
	String springMailPassword;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	String springMailPropertiesMailSmtpAuth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	String springMailPropertiesMailSmtpStarttlsEnable;
	
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(springMailHost);
		mailSender.setPort(springMailPort);

		mailSender.setUsername(springMailUserName);
		mailSender.setPassword(springMailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl", "true");

		return mailSender;
	}
	
	public String getPasswordResetToken() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
}
