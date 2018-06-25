package com.jf.mail;

import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	public static void main(String[] args) {
		String ToEmail = "fandz8660@qq.com";
		String EmailCode = UUID.randomUUID().toString();
		try {
			sendEmail(ToEmail,EmailCode);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用465端口发送邮件
	 * 视频里用的是25端口，但现在网络服务器把25端口禁了
	 * 导致只要使用他们的服务器，邮件就发不出去
	 * 所以使用465端口
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendEmail(String ToEmail,String EmailCode) throws AddressException, MessagingException{
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  //设置邮件的会话参数
		  Properties props = System.getProperties();
		  //邮箱发送服务器的地址，163的地址是smtp.163.com
		  props.setProperty("mail.smtp.host", "smtp.qq.com");
		  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		  props.setProperty("mail.smtp.socketFactory.fallback", "false");
		  //这里设置邮件发送的端口，设置为465
		  props.setProperty("mail.smtp.port", "465");
		  props.setProperty("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.auth", "true");
		  final String username = "fandz8660@qq.com";//服务器端的邮箱账号，也就是发件人账号，几乎不变
		  final String password = "pjyjzgzvmregggeh";//这个是授权码
		  //获取到邮箱会话，利用了匿名内部类的方式，将发件人的邮箱的用户名和授权码给jvm
		  Session session = Session.getDefaultInstance(props, new Authenticator(){
		      protected PasswordAuthentication getPasswordAuthentication() {
		          return new PasswordAuthentication(username, password);
		      }});
		  session.setDebug(true);
		  //通过会话得到一个邮件
		  Message msg = new MimeMessage(session);
		  //设置邮箱内容，可以嵌套HTML
		  String mm="<font style='color:orange;font-width:600;font-size:24px;'>"+EmailCode+"</font>";
		  msg.setFrom(new InternetAddress(username));//设置发件人
		  //设置收件人，这个收件人一般情况下，是从数据库中获得的 TO是收件人， CC是抄送  BCC是密送
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToEmail,false));
		  //设置邮箱主题
		  msg.setSubject("fish音乐激活邮件");
		  //设置邮件正文  setContext可以发送含有HTML的文件，setText不可以
		  msg.setContent("激活码为:"+mm+",请复制此激活码到页面进行激活","text/html;charset=utf-8");
		  msg.setSentDate(new Date());//发送日期
		  Transport.send(msg);//
	}
}
