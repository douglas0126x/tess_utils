package com.ele.test.mail;

import java.io.File;

import com.ele.utils.mail.MailFactory;

public class Mail_163_test {

	
	public static void main(String[] args) {
//		SendMailUtil send = new SendMailUtil();
		MailFactory  send = new MailFactory();
		
		File[] attach = new File[2];
		attach[0] = new File("F:/generate_test/title.png");
		attach[1] = new File("F:/generate_test/list_pdf.pdf");
		send.send_mail("9:18", "邮件正文", "yaoxiaojun@ele-cloud.com", "", "", attach);
		
		System.out.println("success");
	}
}
