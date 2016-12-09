package com.ichunming.mg.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.common.constant.UserType;
import com.ichunming.mg.common.util.DateUtil;
import com.ichunming.mg.common.util.RandomUtil;
import com.ichunming.mg.common.util.StringUtil;
import com.ichunming.mg.common.util.helper.EmailConfiguration;
import com.ichunming.mg.core.exception.LoadTemplateException;
import com.ichunming.mg.core.service.EmailService;
import com.ichunming.mg.core.service.SmsService;
import com.ichunming.mg.dao.VerifyInfoDao;
import com.ichunming.mg.entity.EmailTplMsgEntity;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.model.VerifyInfo;
import com.ichunming.mg.service.IVerifyService;

@Service
public class VerifyServiceImpl implements IVerifyService {

	private static final Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);
	
	@Autowired
	private VerifyInfoDao verifyDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private EmailConfiguration emailConfig;

	@Override
	public BaseResult sendCodeByEmail(String email) {
		// generate validate code
		logger.debug("generate validate code...");
		String code = RandomUtil.genDigitalString(6);
		
		// send validate code
		logger.debug("send validate code...");
		EmailTplMsgEntity msg = new EmailTplMsgEntity();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		msg.setSubject(emailConfig.getVerifyCodeSubject());
		try {
			msg.setContent(StringUtil.loadTemplate(emailConfig.getVerifyCodeMsgTpl(), param));
		} catch (Exception e) {
			logger.error("load template fail.");
			e.printStackTrace();
			throw new LoadTemplateException(e);
		}
		msg.setTo(email);
		
		if(!emailService.sendHtml(msg)) {
			return new BaseResult(ErrorCode.ERR_SVR_EMAIL_ERROR, "Email Service Error");
		}
		
		// save validate code
		logger.debug("save validate code...");
		VerifyInfo verifyInfo = new VerifyInfo();
		verifyInfo.setContent(code);
		verifyInfo.setReceiver(email);
		verifyInfo.setType(UserType.EMAIL.getCode());
		verifyInfo.setCreateDate(DateUtil.current());
		verifyInfo.setExpireDate(DateUtil.dateAfter(DateUtil.current(), 5, Calendar.MINUTE));
		verifyDao.insert(verifyInfo);
		
		return new BaseResult(ErrorCode.SUCCESS);
	}

	@Override
	public BaseResult sendCodeByMobile(String mobile) {
		// frequency check
		VerifyInfo recentInfo = verifyDao.getRecent(mobile);
		if(null != recentInfo) {
			// send sms frequent
			logger.debug("send sms too frequent, please send sms after 30s.");
			return new BaseResult(ErrorCode.ERR_USER_SEND_SMS_FREQUENT, "Send Message Too Frequent");
		}
		
		// generate validate code
		logger.debug("generate validate code...");
		String code = RandomUtil.genDigitalString(6);
		
		// send validate code
		logger.debug("send validate code...");
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", code);
		if(!smsService.sendValidation(Arrays.asList(mobile), param)) {
			return new BaseResult(ErrorCode.ERR_SVR_SMS_ERROR, "SMS Service Error");
		}

		// save validate code
		logger.debug("save validate code...");
		VerifyInfo verifyInfo = new VerifyInfo();
		verifyInfo.setContent(code);
		verifyInfo.setReceiver(mobile);
		verifyInfo.setType(UserType.MOBILE.getCode());
		verifyInfo.setCreateDate(DateUtil.current());
		verifyInfo.setExpireDate(DateUtil.dateAfter(DateUtil.current(), 5, Calendar.MINUTE));
		verifyDao.insert(verifyInfo);
		
		return new BaseResult(ErrorCode.SUCCESS);
	}
	
	@Override
	public BaseResult verifyCode(String receiver, int type, String code) {
		// verify code
		logger.debug("verify code[" + code + "]");
		VerifyInfo verifyInfo = new VerifyInfo();
		verifyInfo.setReceiver(receiver);
		verifyInfo.setType(type);
		verifyInfo.setContent(code);
		
		// get information
		logger.debug("get information about code...");
		verifyInfo = verifyDao.getByContent(verifyInfo);
		
		// check code
		logger.debug("check code...");
		if(null == verifyInfo) {
			// invalid
			logger.debug("code invalid.");
			return new BaseResult(ErrorCode.ERR_USER_VERIFY_CODE_INVALID, "Code Invalid");
		} else if(DateUtil.isDateTimeAfter(DateUtil.current(), verifyInfo.getExpireDate())) {
			// overdue
			logger.debug("code overdue.");
			return new BaseResult(ErrorCode.ERR_USER_VERIFY_CODE_OVERDUE, "Code Overdue");
		} else {
			// resave to history
			logger.debug("resave to history...");
			verifyDao.delete(verifyInfo.getId());
			verifyDao.insertToHis(verifyInfo);
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}
}
