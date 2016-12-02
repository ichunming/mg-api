/**
 * UserController
 * create by ming 2016/11/15
 * v0.1
 */
package com.ichunming.mg.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.common.constant.SystemConstant;
import com.ichunming.mg.common.util.CookieUtil;
import com.ichunming.mg.common.util.SessionUtil;
import com.ichunming.mg.common.util.StringUtil;
import com.ichunming.mg.entity.SessionInfo;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.service.IUserService;

@Controller
@ResponseBody
@RequestMapping("/v1/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public BaseResult register(String username, String password, String code) {
		// 用户注册
		logger.debug("user register...");
		
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			// 使用邮箱注册
			return userService.registerByEmail(username, password, code);
		} else if(StringUtil.isMobile(username)) {
			// 使用手机注册
			logger.debug("register by mobile...");
			return userService.registerByMobile(username, password, code);
		} else {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID);
		}
	}
	
	@RequestMapping(value = "verifycode/get", method = RequestMethod.POST)
	public BaseResult getCode(String username) {
		// 获取验证码
		logger.debug("get verify code...");
		
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			// 邮箱发送认证code
			logger.debug("send code by email...");
			return userService.sendCodeByEmail(username);
		} else if(StringUtil.isMobile(username)) {		
			// 手机发送认证code
			logger.debug("send code by mobile...");
			return userService.sendCodeByMobile(username);
		} else {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID);
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public BaseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = null;
		// 用户登入
		logger.debug("user login...");
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			// 使用邮箱登入
			logger.debug("login with email...");
			result = userService.login(username, password);
		} else if(StringUtil.isMobile(username)) {
			// 使用手机登入
			logger.debug("login with mobile...");
			result = userService.login(username, password);
		} else {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID);
		}
		
		if(result.getCode().longValue() == ErrorCode.SUCCESS.longValue()) {
			// 保存SessionInfo
			logger.debug("save session info...");
			SessionInfo sessionInfo = (SessionInfo)result.getData();
			SessionUtil.setSessionInfo(sessionInfo, request);
			
			// 保存cookie
			try {
				if(!StringUtil.isEmpty(sessionInfo.getNickname())) {
					CookieUtil.setCookie(response, SystemConstant.DEFAULT_COOKIES_NICKNAME,  URLEncoder.encode(sessionInfo.getNickname(), "utf-8"));
				}
				if(!StringUtil.isEmpty(sessionInfo.getPortrait())) {
					CookieUtil.setCookie(response, SystemConstant.DEFAULT_COOKIES_HEADIMG,  URLEncoder.encode(sessionInfo.getPortrait(), "utf-8"));
				}
			} catch (Exception e) {
				logger.debug("set cookie fail.", e);
			}
			
			// 返回值
			result.setData(null);
		}
		
		return result;
	}
	
	@RequestMapping(value = "password/reset", method = RequestMethod.POST)
	public BaseResult resetPwd(String oldPwd, String pwd) {
		// 密码重置
		logger.debug("reset user password...");
		// TODO
		return null;
	}
	
	@RequestMapping(value = "profile/get", method = RequestMethod.POST)
	public BaseResult getProfile() {
		// 用户信息取得
		logger.debug("get user profile...");
		// TODO
		return new BaseResult(ErrorCode.SUCCESS);
	}
	
	@RequestMapping(value = "profile/save", method = RequestMethod.POST)
	public BaseResult saveProfile(String email, String mobile, String nickname, String realname) {
		// 用户信息保存
		logger.debug("save user profile...");
		// TODO
		return null;
	}

	@RequestMapping(value = "profile/portrait/upload", method = RequestMethod.POST)
	public BaseResult uploadPortrait(MultipartFile portrait) {
		// 用户头像上传
		logger.debug("upload user portrait...");
		// TODO
		return null;
	}
}
