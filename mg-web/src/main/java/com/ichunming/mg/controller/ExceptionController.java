package com.ichunming.mg.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.core.exception.ApplicationException;
import com.ichunming.mg.core.exception.EmailServiceException;
import com.ichunming.mg.core.exception.InvalidSessionException;
import com.ichunming.mg.core.exception.LoadTemplateException;
import com.ichunming.mg.core.exception.OssServiceException;
import com.ichunming.mg.core.exception.SmsServiceException;
import com.ichunming.mg.entity.vo.BaseResult;

@ControllerAdvice
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET},headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult handleGeneralException(Exception ex) {
		
		if(ex instanceof ApplicationException) {
			logger.error("Application Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "Internal Error");
		} else if(ex instanceof IllegalArgumentException) {
			logger.error("Illegal Argument Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "Illegal Argument Error");
		} else if(ex instanceof SQLException) {
			logger.error("Database Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_DATABASE_ERROR, "Database Error");
		} else if(ex instanceof HttpRequestMethodNotSupportedException) {
			logger.error("Not Supported Http Request Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_METHOD_NOT_SUPPORT, "Not Supported Http Request Error");
		} else if(ex instanceof InvalidSessionException) {
			logger.error("No Session Error.", ex);
			return new BaseResult(ErrorCode.ERR_USER_NO_SESSION, "No Session Error");
		} else if(ex instanceof EmailServiceException) {
			logger.error("Email Service Error.", ex);
			return new BaseResult(ErrorCode.ERR_SVR_EMAIL_ERROR, "Email Service Error");
		} else if(ex instanceof OssServiceException) {
			logger.error("Oss Service Error.", ex);
			return new BaseResult(ErrorCode.ERR_SVR_OSS_ERROR, "Oss Service Error");
		} else if(ex instanceof SmsServiceException) {
			logger.error("Sms Service Error.", ex);
			return new BaseResult(ErrorCode.ERR_SVR_SMS_ERROR, "Sms Service Error");
		} else if(ex instanceof LoadTemplateException) {
			logger.error("Load Template Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_LOAD_TPL_FAIL, "Load Template Error");
		} else {
			logger.error("Unknown Error.", ex);
			return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "Unknown Error");
		}
	}
}
