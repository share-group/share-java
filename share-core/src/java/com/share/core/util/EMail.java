package com.share.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 电子邮件类(基于javax.mail实现)
 * @author ruan
 *
 */
public class EMail {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(EMail.class);
	/**
	 * smtp服务器地址
	 */
	private final static String smtpHost = FileSystem.getPropertyString("mail.smtp.host");
	/**
	 * 发件人用户名
	 */
	private final static String senderUser = FileSystem.getPropertyString("mail.sender.user");
	/**
	 * 发件人密码
	 */
	private final static String senderPass = FileSystem.getPropertyString("mail.sender.pass");
	/**
	 * 邮件地址别名
	 */
	private final static String senderAlias = FileSystem.getPropertyString("mail.smtp.alias");
	/**
	 * 消息结构体
	 */
	private MimeMessage message;
	/**
	 * session会话
	 */
	private Session s;

	/**
	 * 构造函数
	 */
	private EMail() {
	}

	/**
	 * 发送邮件
	 * @author ruan
	 * @param title 邮件标题
	 * @param mailType 邮件类型(可选：纯文本、html格式)
	 * @param content 邮件内容
	 * @param fileList 附件列表
	 * @param receiver 收件人
	 */
	public void send(String title, MailType mailType, String content, List<String> fileList, String... receiver) {
		Transport transport = null;

		try {
			SmtpAuth smtpAuth = new SmtpAuth();
			smtpAuth.setUser(senderUser);
			smtpAuth.setPassword(senderPass);

			s = Session.getInstance(FileSystem.getProperty(), smtpAuth);
			message = new MimeMessage(s);

			// 设置发件人信息
			InternetAddress from = null;
			if (senderAlias.isEmpty()) {
				from = new InternetAddress(senderUser);
			} else {
				from = new InternetAddress(senderUser, senderAlias);
			}
			message.setFrom(from);

			// 收件人
			StringBuilder sb = new StringBuilder();
			for (String s : receiver) {
				sb.append(s.trim());
				sb.append(",");
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sb.toString()));

			// 邮件标题
			message.setSubject(title.trim());

			// 邮件内容
			Multipart mailPart = new MimeMultipart();

			// 文字内容
			MimeBodyPart mailContent = new MimeBodyPart();
			mailContent.setContent(content.trim(), "text/" + mailType.toString() + ";charset=" + SystemUtil.getSystemCharsetString());
			mailPart.addBodyPart(mailContent);

			// 添加附件
			for (String file : fileList) {
				MimeBodyPart attachment = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(file.trim());
				attachment.setDataHandler(new DataHandler(fileDataSource));
				attachment.setFileName(fileDataSource.getName().trim());
				mailPart.addBodyPart(attachment);
			}

			// 设置邮件内容
			message.setContent(mailPart);
			message.setSentDate(new Date());
			message.saveChanges();
			transport = s.getTransport("smtp");

			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(smtpHost, senderUser, senderPass);

			// 发送
			Address[] allRecipients = message.getAllRecipients();
			transport.sendMessage(message, allRecipients);

			logger.warn("mail content: {}", content);
			logger.warn("all recipients : {}", JSONObject.encode(allRecipients));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 发送邮件
	 * @author ruan
	 * @param title 邮件标题
	 * @param mailType 邮件类型(可选：纯文本、html格式)
	 * @param content 邮件内容
	 * @param receiver 收件人
	 */
	public void send(String title, MailType mailType, String content, String... receiver) {
		send(title, mailType, content, new ArrayList<String>(0), receiver);
	}

	/**
	 * 发送邮件
	 * @author ruan
	 * @param title 邮件标题
	 * @param mailType 邮件类型(可选：纯文本、html格式)
	 * @param content 邮件内容
	 * @param fileList 附件列表
	 * @param receiver 收件人
	 */
	public void send(String title, MailType mailType, String content, ArrayList<File> fileList, String... receiver) {
		List<String> filePathList = new ArrayList<String>();
		for (File file : fileList) {
			filePathList.add(file.getPath());
		}
		send(title, mailType, content, filePathList, receiver);
	}

	/**
	 * 定义一个SMTP授权验证类
	 * @author ruan
	 */
	private static class SmtpAuth extends Authenticator {
		String user;//帐号
		String password;//密码

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}

		public void setUser(String user) {
			this.user = user;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	/**
	 * 邮件类型
	 * @author ruan
	 *
	 */
	public final static class MailType {
		/**
		 * 文本
		 */
		public final static MailType PLAIN = new MailType("plain");
		/**
		 * html
		 */
		public final static MailType HTML = new MailType("html");
		/**
		 * 邮件类型描述
		 */
		private String type;

		/**
		 * 构造函数
		 * @param type
		 */
		private MailType(String type) {
			this.type = type.toLowerCase();
		}

		/**
		 * toString
		 * @author ruan
		 * @return
		 */
		public String toString() {
			return type;
		}
	}
}