package com.shop.www.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageListenerImpl implements MessageListener{

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String messageDetail =textMessage.getText();
			System.out.println("�յ���Ϣ����Ϣ���飺"+messageDetail);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
