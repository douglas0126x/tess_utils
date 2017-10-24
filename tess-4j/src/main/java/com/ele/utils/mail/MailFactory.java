package com.ele.utils.mail;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import javax.mail.internet.MimeUtility;




/**
 * 邮件生产
 * @author yaoxj
 * @time 2017年10月24日下午7:39:26
 */
public class MailFactory {
	
	/**
	 * 邮件常量信息
	 */
	private MailConstant constant = new MailConstant();
	
	
	/**
     * 发送邮件
     * @param subject
     * 				邮件主题
     * @param sendHtml
     * 				邮件内容
     * @param toUser
     * 				收件人  多个时参数形式  ：  "xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param ccUser
     * 				抄送人   同上
     * @param bccUser
     * 				密送人   同上
     * @param attachment
     * 				附件
     */
	public boolean send_mail(String subject, String sendHtml,String  toUser, String ccUser, String bccUser, File [] attachment){
		//链接属性
		
		PopupAuthenticator auth = new PopupAuthenticator();
		//环境
		Session session = Session.getInstance(constant.get_props(), auth);
		session.setDebug(true);// 打印Debug信息
		
		//邮件
		MimeMessage message = new MimeMessage(session);
		
		try {
		
			//方式1：邮箱昵称
//			Address addressFrom = new InternetAddress(PopupAuthenticator.mailuser + "@qq.com", "test");// 第一个参数为发送方电子邮箱地址；第二个参数为发送方邮箱地址的标签
//			Address addressTo = new InternetAddress("yaoxiaojun@ele-cloud.com",	"hello");// 第一个参数为接收方电子邮箱地址；第二个参数为接收方邮箱地址的标签
			
			
			//方式2：
			// 发件人
            InternetAddress from = new InternetAddress(PopupAuthenticator.mailuser + "@qq.com");
            message.setFrom(from);
            
            // 设置多个收件人地址
            if(null != toUser && !toUser.isEmpty()){
	            @SuppressWarnings("static-access")
				InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);
	            message.setRecipients(Message.RecipientType.TO, internetAddressTo);
            }
            
            // 设置多个抄送地址
            if(null != ccUser && !ccUser.isEmpty()){
	            @SuppressWarnings("static-access")
				InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
	            message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            
            // 设置多个密送地址
            if(null != bccUser && !bccUser.isEmpty()){
	            @SuppressWarnings("static-access")
	            InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
	            message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
            }
            
            // 发送日期
            message.setSentDate(new Date()); 
            
            // 邮件主题
            message.setSubject(subject);
            
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, constant.getContentPart_type());
            multipart.addBodyPart(contentPart);
            
            BodyPart attachmentBodyPart = null;
            // 添加附件的内容
            if (null != attachment && attachment.length != 0) {
            	for (File file : attachment) {
            		attachmentBodyPart = new MimeBodyPart();
            		
            		DataSource source = new FileDataSource(file);
            		attachmentBodyPart.setDataHandler(new DataHandler(source));
            		//MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
                    multipart.addBodyPart(attachmentBodyPart);
				}
            }
            
            // 将multipart对象放到message中
            message.setContent(multipart);
            
            // 保存邮件
            message.saveChanges();
            
			
			Transport transport = session.getTransport(MailConstant.getProtocol());
			transport.connect(MailConstant.getHostAddress(), PopupAuthenticator.mailuser,PopupAuthenticator.password);
			
			transport.sendMessage(message,message.getAllRecipients());
			
			transport.close();
			System.out.println("发送成功");
			return true;
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("发送失败");
			return false;
		}
		
		
		
		
	}



	
	
	
	
	
	
	
	
}


/**
 * smtp登录邮箱,认证信息准备
 * @author yaoxj
 * @time 2017年10月24日下午8:06:32
 */
class PopupAuthenticator extends Authenticator {
	private static MailConstant constant = new MailConstant();
	public static final String mailuser = constant.getMailUser();// 发送方邮箱'@'符号前的内容:1453296946@qq.com
	public static final String password = constant.getPassword();// 成功开启IMAP/SMTP服务，在第三方客户端登录时，腾讯提供的密码。注意不是邮箱密码

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mailuser, password);
	}
}
