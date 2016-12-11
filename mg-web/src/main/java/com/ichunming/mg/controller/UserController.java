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
import com.ichunming.mg.core.configuration.ApiConfiguration;
import com.ichunming.mg.entity.SessionInfo;
import com.ichunming.mg.entity.UserView;
import com.ichunming.mg.model.UserProfile;
import com.ichunming.mg.service.IUserService;
import com.ichunming.mg.vo.BaseResult;
import com.ichunming.mg.vo.UserProfileVo;

@Controller
@ResponseBody
@RequestMapping("/v1/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ApiConfiguration apiConfig;
	
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
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "parameter invalid");
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
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "parameter invalid");
		}
		
		if(result.getCode().longValue() == ErrorCode.SUCCESS.longValue()) {
			// 保存SessionInfo
			logger.debug("save session info...");
			UserView user = (UserView)result.getData();
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.fromView(user);
			SessionUtil.setSessionInfo(sessionInfo, request);
			
			// 保存cookie
			try {
				if(!StringUtil.isEmpty(user.getNickname())) {
					CookieUtil.setCookie(response, SystemConstant.COOKIES_NICKNAME_NAME,  URLEncoder.encode(user.getNickname(), "utf-8"), apiConfig.getDomainUrl());
				}
				if(!StringUtil.isEmpty(user.getPortrait())) {
					CookieUtil.setCookie(response, SystemConstant.COOKIES_HEADIMG_NAME,  URLEncoder.encode(user.getPortrait(), "utf-8"), apiConfig.getDomainUrl());
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
	public BaseResult resetPwd(String oldPwd, String newPwd, HttpServletRequest request) {
		// 密码重置
		logger.debug("reset user password...");
		// 参数check
		if(!StringUtil.isPassword(oldPwd) || !StringUtil.isPassword(newPwd)) {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "parameter invalid");
		}

		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		return userService.resetPwd(sessionInfo.getUid(), oldPwd, newPwd);
	}
	
	@RequestMapping(value = "profile/get", method = RequestMethod.POST)
	public BaseResult getProfile(HttpServletRequest request) {
		// 用户信息取得
		logger.debug("get user profile...");

		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		return userService.getProfile(sessionInfo.getUid());
	}
	
	@RequestMapping(value = "profile/save", method = RequestMethod.POST)
	public BaseResult saveProfile(UserProfileVo profileVo, HttpServletRequest request, HttpServletResponse response) {
		// 用户信息保存
		logger.debug("save user profile...");
		
		// 参数check
		logger.debug("check parameter...");
		String checkResult = profileVo.check();
		if(!StringUtil.isEmpty(checkResult)) {
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, checkResult);
		}
		
		// 保存信息
		logger.debug("save profile...");
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		UserProfile profile = profileVo.toUserProfile();
		profile.setUid(sessionInfo.getUid());
		userService.saveProfile(profile);
		
		// 更新cookie
		logger.debug("update cookie...");
		try {
			if(!StringUtil.isEmpty(profileVo.getNickname())) {
				CookieUtil.setCookie(response, SystemConstant.COOKIES_NICKNAME_NAME,  URLEncoder.encode(profileVo.getNickname(), "utf-8"), apiConfig.getDomainUrl());
			}
		} catch (Exception e) {
			logger.debug("set cookie fail.", e);
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}

	@RequestMapping(value = "profile/portrait/upload", method = RequestMethod.POST)
	public BaseResult uploadPortrait(MultipartFile portrait) {
		// 用户头像上传
		logger.debug("upload user portrait...");
		// TODO
		return null;
	}
}
