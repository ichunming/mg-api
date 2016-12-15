/**
 * email service
 * ming 2016/11/18
 */
package com.mg.api.core.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.api.common.util.EmailUtil;
import com.mg.api.core.configuration.EmailConfiguration;
import com.mg.api.core.exception.EmailServiceException;
import com.mg.api.entity.EmailTplMsgEntity;

@Service
public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private EmailConfiguration config;
	
	/**
	 * send simple mail
	 * @param msg
	 * @return
	 */
	public boolean send(EmailTplMsgEntity msg) {
		
		boolean result = true;
		logger.debug("send simple mail to[" + msg.getTo() + "].");
		try {
			result =  EmailUtil.send(this.config, msg.getSubject(), msg.getContent(), msg.getTo());
		} catch (Exception e) {
			logger.error("send simple mail to[" + msg.getTo() + "] fail.", e);
			throw new EmailServiceException(e);
		}
		
		return result;
	}

	/**
	 * send mail with attachment
	 * @param msg
	 * @param attachs
	 * @return
	 */
	public boolean send(EmailTplMsgEntity msg, List<Map<String, String>> attachs) {

		boolean result = true;
		logger.debug("send mail with attachment to[" + msg.getTo() + "].");
		try {
			result =  EmailUtil.send(this.config, msg.getSubject(), msg.getContent(), msg.getTo(), attachs);
		} catch (Exception e) {
			logger.error("send mail with attachment to[" + msg.getTo() + "] fail.");
			throw new EmailServiceException(e);
		}
		
		return result;
	}

	/**
	 * send html mail
	 * @param msg
	 * @return
	 */
	public boolean sendHtml(EmailTplMsgEntity msg) {

		boolean result = true;
		logger.debug("send html mail to[" + msg.getTo() + "].");
		try {
			result =  EmailUtil.sendHtml(this.config, msg.getSubject(), msg.getContent(), msg.getTo());
		} catch (Exception e) {
			logger.error("send html mail to[" + msg.getTo() + "] fail.");
			throw new EmailServiceException(e);
		}
		
		return result;
	}

	/**
	 * send html mail with attachment
	 * @param msg
	 * @param attachs
	 * @return
	 */
	public boolean sendHtml(EmailTplMsgEntity msg, List<Map<String, String>> attachs) {

		boolean result = true;
		logger.debug("send html mail with attachment to[" + msg.getTo() + "].");
		try {
			result =  EmailUtil.sendHtml(this.config, msg.getSubject(), msg.getContent(), msg.getTo(), attachs);
		} catch (Exception e) {
			logger.error("send html mail with attachment to[" + msg.getTo() + "] fail.");
			throw new EmailServiceException(e);
		}
		
		return result;
	}
}