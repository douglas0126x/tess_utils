package com.ele.utils.mail;

import java.util.Properties;


/**
 * 邮件服务常量
 * @author yaoxj
 * @time 2017年10月24日下午7:49:04
 */
public class MailConstant {
	
	/**
	 * 邮箱服务地址
	 */
	private static final String host_address = "smtp.qq.com";
	/**
	 * 邮件服务协议
	 */
	private static final String protocol = "smtp";
	/**
	 * 服务端口
	 */
	private static final String host_port = "587";// 使用465或587端口
	/**
	 * 登录认证
	 */
	private static final String is_auth = "true";// 设置使用验证
	/**
	 * STARTTLS安全连接
	 */
	private static final String is_starttls = "true";// 使用 STARTTLS安全连接
	/**
	 * 邮箱账户
	 */
	private final String mail_user = "2604222937";// 发送方邮箱'@'符号前的内容:1453296946@qq.com
	/**
	 * 邮箱smtp密码！
	 */
	private  final String password = "fdmbhiecehveecic";// 成功开启IMAP/SMTP服务，在第三方客户端登录时，腾讯提供的密码。注意不是邮箱密码
	/**
	 * 邮件正文编码
	 */
	private final String contentPart_type = " text/html;charset=UTF-8";
	
	/**
	 * 获取链接属性
	 * @return
	 */
	public  Properties  get_props(){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host_address);
		props.put("mail.smtp.port", host_port);
		props.put("mail.smtp.auth", is_auth);
		props.put("mail.smtp.starttls.enable", is_starttls);
		
		return props;
		
	}
	
	/**
	 * 获取邮箱账户
	 * @return
	 */
	public  String getMailUser() {
		return mail_user;
	}
	/**
	 * 邮箱密码
	 * @return
	 */
	public  String getPassword() {
		return password;
	}
	
	/**
	 * 获取邮件传输协议
	 * @return
	 */
	public static String getProtocol() {
		return protocol;
	}

	public static String getHostAddress() {
		return host_address;
	}

	public static String getHostPort() {
		return host_port;
	}

	public static String getIsAuth() {
		return is_auth;
	}

	public static String getIsStarttls() {
		return is_starttls;
	}

	public String getMail_user() {
		return mail_user;
	}

	public String getContentPart_type() {
		return contentPart_type;
	}
	
	
	
	
	
	
	
	
}
