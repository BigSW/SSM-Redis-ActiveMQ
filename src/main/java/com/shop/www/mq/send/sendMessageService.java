package com.shop.www.mq.send;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class sendMessageService implements IsendMessageService {

	private JmsTemplate jmsTemplate;
	private Destination destination =null;

	public void send(final String message) {
		if(destination==null){
			destination= jmsTemplate.getDefaultDestination();
		}
		System.out.println("---------------生产者发了一个消息：" + message+";Address"+destination); 
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
