package com.ele.test.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ele.utils.mail.MailConstant;

/**
 * 使用QQ邮箱IMAP/SMTP的实现发送电子邮件 2015-12-06
 */
public class QQ_mail_success {
	public static void main(String[] args) {
		
		final String to_address = "yaoxiaojun@ele-cloud.com";
		
		MailConstant constant = new MailConstant();
		/*
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		*/
		
		
		try {
			
			PopupAuthenticator auth = new PopupAuthenticator();
			Session session = Session.getInstance(constant.get_props(), auth);
			session.setDebug(true);// 打印Debug信息
			
			
			
			MimeMessage message = new MimeMessage(session);
			Address addressFrom = new InternetAddress(PopupAuthenticator.mailuser + "@qq.com", "test");// 第一个参数为发送方电子邮箱地址；第二个参数为发送方邮箱地址的标签
			Address addressTo = new InternetAddress(to_address,	"hello");// 第一个参数为接收方电子邮箱地址；第二个参数为接收方邮箱地址的标签
			message.setSubject("hello");
			message.setText("Nice to meet you !");
			message.setFrom(addressFrom);
//			message.setRecipients(Message.RecipientType.CC,(Address[]) InternetAddress.parse(to_address));
			message.setRecipients(Message.RecipientType.TO,	(Address[])InternetAddress.parse(to_address));
			
			
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource("F:/generate_test/title.png");
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bp);
			
			
			bp.setContent("", "text/html;charset=GBK");
			mp.addBodyPart(bp);
			
			
			message.setContent(mp);
			message.saveChanges();
			
			
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com", PopupAuthenticator.mailuser,PopupAuthenticator.password);
			
			transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
//			transport.sendMessage(message,message.getRecipients(Message.RecipientType.CC));
			transport.send(message);
			
			
			transport.close();
			System.out.println("发送成功");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("发送失败");
		}
	}
}

class PopupAuthenticator extends Authenticator {
	public static final String mailuser = "2604222937";// 发送方邮箱'@'符号前的内容:1453296946@qq.com
	public static final String password = "fdmbhiecehveecic";// 成功开启IMAP/SMTP服务，在第三方客户端登录时，腾讯提供的密码。注意不是邮箱密码

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mailuser, password);
	}
}