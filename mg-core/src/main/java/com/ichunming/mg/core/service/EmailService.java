/**
 * email service
 * create by ming 2016/11/18
 */
package com.ichunming.mg.core.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichunming.mg.common.util.EmailUtil;
import com.ichunming.mg.common.util.helper.MailConfiguration;

@Service
public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private MailConfiguration config;
	
	/**
	 * send simple mail
	 * @param subject
	 * @param content
	 * @param to
	 * @return
	 */
	public boolean send(String subject, String content, String to) {
		
		boolean result = true;
		logger.debug("send simple mail to[" + to + "].");
		try {
			result =  EmailUtil.send(this.config, subject, content, to);
		} catch (EmailException e) {
			logger.debug("send simple mail to[" + to + "] fail.");
			System.out.println(e);
			return false;
		}
		
		return result;
	}

	/**
	 * send mail with attachment
	 * @param subject
	 * @param content
	 * @param to
	 * @param attachs
	 * @return
	 */
	public boolean send(String subject, String content, String to, List<Map<String, String>> attachs) {

		boolean result = true;
		logger.debug("send mail with attachment to[" + to + "].");
		try {
			result =  EmailUtil.send(this.config, subject, content, to, attachs);
		} catch (EmailException e) {
			logger.debug("send mail with attachment to[" + to + "] fail.");
			return false;
		}
		
		return result;
	}

	/**
	 * send html mail
	 * @param subject
	 * @param content
	 * @param to
	 * @return
	 */
	public boolean sendHtml(String subject, String content, String to) {

		boolean result = true;
		logger.debug("send html mail to[" + to + "].");
		try {
			result =  EmailUtil.sendHtml(this.config, subject, content, to);
		} catch (EmailException e) {
			logger.debug("send html mail to[" + to + "] fail.");
			return false;
		}
		
		return result;
	}

	/**
	 * send mail with attachment
	 * @param subject
	 * @param content
	 * @param to
	 * @param attachs
	 * @return
	 */
	public boolean sendHtml(String subject, String content, String to, List<Map<String, String>> attachs) {

		boolean result = true;
		logger.debug("send html mail with attachment to[" + to + "].");
		try {
			result =  EmailUtil.sendHtml(this.config, subject, content, to, attachs);
		} catch (EmailException e) {
			logger.debug("send html mail with attachment to[" + to + "] fail.");
			return false;
		}
		
		return result;
	}
}