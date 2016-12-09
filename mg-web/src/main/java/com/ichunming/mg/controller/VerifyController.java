package com.ichunming.mg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.common.util.StringUtil;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.service.IVerifyService;

@Controller
@ResponseBody
@RequestMapping("/v1/verify")
public class VerifyController {
	
	private static final Logger logger = LoggerFactory.getLogger(VerifyController.class);
	
	@Autowired
	private IVerifyService verifyService;
	
	@RequestMapping(value = "code/get", method = RequestMethod.POST)
	public BaseResult getCode(String username) {
		// 获取验证码
		logger.debug("get verify code...");
		
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			// 邮箱发送认证code
			logger.debug("send code by email...");
			return verifyService.sendCodeByEmail(username);
		} else if(StringUtil.isMobile(username)) {		
			// 手机发送认证code
			logger.debug("send code by mobile...");
			return verifyService.sendCodeByMobile(username);
		} else {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "parameter invalid");
		}
	}
}
