/**
 * UserController
 * ming 2016/11/15
 */
package com.mg.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mg.api.common.constant.ErrorCode;
import com.mg.api.vo.BaseResult;

@Controller
@RequestMapping("/")
public class ErrorRequestController {
	private static Logger logger = LoggerFactory.getLogger(ErrorRequestController.class);
	
	@RequestMapping(value = "/404", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult handle404Error(HttpServletRequest request,HttpServletResponse response){						
		logger.debug("404 Error.");
		return new BaseResult(ErrorCode.ERR_SYS_NO_RESOURCE_FOUND, "404 error");
	}
	
	@RequestMapping(value = "/500", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult handle500Error(HttpServletRequest request,HttpServletResponse response){
		logger.debug("505 Error.");
		return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "500 error");
	}
	
	@RequestMapping(value = "/400", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult handle400Error(HttpServletRequest request,HttpServletResponse response){
		logger.debug("400 Error.");
		return new BaseResult(ErrorCode.ERR_SYS_BAD_REQUEST, "400 error");
	}
}
