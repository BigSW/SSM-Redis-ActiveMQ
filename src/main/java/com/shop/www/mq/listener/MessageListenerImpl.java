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
			System.out.println("收到消息：消息详情："+messageDetail);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
