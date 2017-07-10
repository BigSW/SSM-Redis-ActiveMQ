package javaMail;

import com.shop.www.javamail.MailSenderInfo;
import com.shop.www.javamail.SimpleMailSender;

/**
 * 实现原理：
 * 数据库中添加两个字段：
 * status /0:未激活/1:激活			默认为0
 * activeCode:激活码				(随机生成)
 * 
 * 在邮箱发送的数据中的URL中，添加参数用户ID,激活码。用户点击之后。
 * 后台通过用户ID，激活码向数据库中查询，是否存在这个用户，如果存在，把status该成1，如果不存在，重新验证
 * @author bigSW
 *2017年7月10日
 */
public class JavaMailTest {
	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUsername("m*****_1@163.com");
		mailInfo.setPassword("******");// 您的邮箱授权码
		mailInfo.setFromAddress("*******@163.com");
		mailInfo.setToAddress("*****@qq.com");
		mailInfo.setSubject("设置邮箱标题 Java Mail Test");
		mailInfo.setContent("设置邮箱内容 这是一个javax Mail测试,网址：www.baidu.com");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		//sms.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("发送完成");

	}
}
