/**短信发送Service
 * ming 2016/11/24
 */
package com.ichunming.mg.core.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.ichunming.mg.common.util.JsonUtil;
import com.ichunming.mg.common.util.StringUtil;
import com.ichunming.mg.core.configuration.AliConfiguration;
import com.ichunming.mg.core.helper.AliSmsClientWrapper;

@Service
public class SmsService { 
	
	private static final Logger logger = LoggerFactory.getLogger(SmsService.class);
	
	@Autowired
	private AliConfiguration config;
	
	/**
	 * 发送验证码短信
	 * @param receiver
	 * @param param
	 * @return
	 */
	public boolean sendValidation(List<String> receiver, Map<String, String> param) {
		
		String receiverStr = StringUtil.toString(receiver, ",");
		logger.debug("send message. receiver[" + receiverStr + "]");
		
		AliSmsClientWrapper smsClient = new AliSmsClientWrapper();
		
		String paramJson = (null == param ? null : JsonUtil.toJson(param));
		
		try {
			smsClient.send(config, receiverStr, this.config.getSmsTplValidateCode(), paramJson);
		} catch(ClientException e) {
			logger.error("send message fail.");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 发送通知短信
	 * @param receiver
	 * @param param
	 * @return
	 */
	public boolean sendNotification(List<String> receiver, Map<String, String> param) {
		
		String receiverStr = StringUtil.toString(receiver, ",");
		logger.debug("send message. receiver[" + receiverStr + "]");
		
		AliSmsClientWrapper smsClient = new AliSmsClientWrapper();
		
		String paramJson = (null == param ? null : JsonUtil.toJson(param));
		
		try {
			smsClient.send(config, receiverStr, this.config.getSmsTplNotifyCode(), paramJson);
		} catch(ClientException e) {
			logger.error("send message fail.");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public void setConfig(AliConfiguration config) {
		this.config = config;
	}
}