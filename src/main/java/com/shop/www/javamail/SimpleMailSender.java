package com.shop.www.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMailSender {
	public boolean sendTextMail(MailSenderInfo mailSenderInfo) {
		MyAuthenticator authenticator = null;
		Properties properties = mailSenderInfo.getProperties();
		if (mailSenderInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailSenderInfo.getUsername(), mailSenderInfo.getPassword());
		}
		// 创建一个session
		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);

		try {
			Message message = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(mailSenderInfo.getFromAddress());
			message.setFrom(from);
			Address to = new InternetAddress(mailSenderInfo.getToAddress());
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(mailSenderInfo.getSubject());
			message.setSentDate(new Date());
			message.setText(mailSenderInfo.getContent());
			Transport.send(message);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendHtmlMail(MailSenderInfo mailSenderInfo) {
		MyAuthenticator authenticator = null;
		Properties properties = mailSenderInfo.getProperties();
		if (mailSenderInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailSenderInfo.getUsername(), mailSenderInfo.getPassword());
		}
		// 创建一个session
		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
		try {
			Message message = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(mailSenderInfo.getFromAddress());
			message.setFrom(from);
			Address to = new InternetAddress(mailSenderInfo.getToAddress());
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(mailSenderInfo.getSubject());
			message.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailSenderInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			message.setContent(mainPart);
			Transport.send(message);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
