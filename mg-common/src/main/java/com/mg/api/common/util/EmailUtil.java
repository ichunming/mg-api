/**
 * mail util
 * created 2016/09/07
 * by ming
 */
package com.mg.api.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.mg.api.entity.EmailOption;

public class EmailUtil {
	
	
	private static final short SIMPLE_MAIL = 0;
	
	private static final short MULTIPART_MAIL = 1;
	
	private static final short HTML_MAIL = 2;
	
	private static final String DEFAULT_CHARSET = "utf-8";
	
	private EmailUtil(){}
	
	/**
	 * 发送简单邮件
	 * @param subject
	 * @param content
	 * @param to
	 * @return
	 * @throws EmailException 
	 */
	public static boolean send(EmailOption option, String subject, String content, String to) throws EmailException {
		SimpleEmail email = (SimpleEmail) createEmail(option, SIMPLE_MAIL);
        // 封装mail
        encapMail(email, subject, content, to, SIMPLE_MAIL);
        // 发送邮件
        email.send();

		return true;
	}
	
	/**
	 * 发送带有附件的简单邮件
	 * @param subject
	 * @param content
	 * @param to
	 * @param attachs
	 * @return
	 * @throws EmailException 
	 */
	public static boolean send(EmailOption option, String subject, String content, String to, List<Map<String, String>> attachs) throws EmailException {
		MultiPartEmail email = (MultiPartEmail) createEmail(option, MULTIPART_MAIL);
		// 封装mail
    	encapMail(email, subject, content, to, MULTIPART_MAIL);
        // 添加附件
    	encapAttach(email, attachs);
    	// 发送邮件
        email.send();

		return true;
	}
	
	/**
	 * 发送html格式邮件
	 * @param option
	 * @param subject
	 * @param content
	 * @param to
	 * @return
	 * @throws EmailException 
	 */
	public static boolean sendHtml(EmailOption option, String subject, String content, String to) throws EmailException {
		HtmlEmail email = (HtmlEmail) createEmail(option, HTML_MAIL);
		// 封装mail
    	encapMail(email, subject, content, to, HTML_MAIL);
    	// 发送邮件
        email.send();

		return true;
	}
	
	/**
	 * 发送带有附件的html格式邮件
	 * @param option
	 * @param subject
	 * @param content
	 * @param to
	 * @param attachs
	 * @return
	 * @throws EmailException 
	 */
	public static boolean sendHtml(EmailOption option, String subject, String content, String to, List<Map<String, String>> attachs) throws EmailException {
		HtmlEmail email = (HtmlEmail) createEmail(option, HTML_MAIL);
		// 封装mail
    	encapMail(email, subject, content, to, HTML_MAIL);
        // 添加附件
    	encapAttach(email, attachs);
    	// 发送邮件
        email.send();

		return true;
	}
	
	/**
	 * 创建Email
	 * @param option
	 * @param type
	 * @return
	 * @throws EmailException
	 */
	private static Email createEmail(EmailOption option, short type) throws EmailException {
		Email email;
		if(type == SIMPLE_MAIL) {
			email = new SimpleEmail();
		} else if(type == MULTIPART_MAIL) {
			email = new MultiPartEmail();
		} else {
			email = new HtmlEmail();
		}
		email.setHostName(option.getHost()); // 发送服务器
        email.setAuthentication(option.getUsername(), option.getPassword()); // 发送邮件的用户名和密码  
        email.setCharset(StringUtil.isEmpty(option.getCharset()) ? DEFAULT_CHARSET : option.getCharset()); //邮件编码方式
        email.setFrom(option.getFrom(), option.getFromName()); // 发送邮箱
		return email;
	}
	
	/**
	 * 封装mail信息
	 * @param email
	 * @param subject
	 * @param content
	 * @param to
	 * @throws EmailException
	 */
	private static void encapMail(Email email, String subject, String content, String to, short type) throws EmailException {
		email.addTo(to);
        email.setSubject(subject); // 主题
        if(type == HTML_MAIL) {
			((HtmlEmail)email).setHtmlMsg(content);
		} else {
			email.setMsg(content); // 内容
		}
	}
	
	/**
	 * 封装附件
	 * @param email
	 * @param attachs
	 * @throws EmailException
	 */
	private static void encapAttach(MultiPartEmail email, List<Map<String, String>> attachs) throws EmailException {
		if(null != attachs && attachs.size() > 0) {
			EmailAttachment att = null;
        	for(Map<String, String> attach : attachs) {
        		att = new EmailAttachment();
        		att.setName(attach.get("name"));
        		att.setPath(attach.get("path"));
        		email.attach(att);
        	}
        }
	}
}
