package vn.framgia.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
@PropertySource(ignoreResourceNotFound = true, value = "classpath:activemq-config.properties")
public class ActiveMqConfig {
	@Value("${broker.url}")
	private String BrokerUrl;
	@Value("${broker.username}")
	private String BrokerUsername;
	@Value("${broker.password}")
	private String BrokerPassword;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
 		connectionFactory.setBrokerURL(BrokerUrl);
		connectionFactory.setUserName(BrokerUsername);
		connectionFactory.setPassword(BrokerPassword);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		return template;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-1");
		factory.setPubSubDomain(false);
		return factory;
	}
}
