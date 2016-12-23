/**
 * UserController
 * ming 2016/11/15
 */
package com.mg.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.SystemConstant;
import com.mg.api.common.constant.SystemSettings;
import com.mg.api.common.constant.ResourceType;
import com.mg.api.common.util.CookieUtil;
import com.mg.api.common.util.DateUtil;
import com.mg.api.common.util.SessionUtil;
import com.mg.api.common.util.StringUtil;
import com.mg.api.core.configuration.AliConfiguration;
import com.mg.api.core.configuration.ApiConfiguration;
import com.mg.api.entity.SessionInfo;
import com.mg.api.entity.UserView;
import com.mg.api.model.UserProfile;
import com.mg.api.service.IFileService;
import com.mg.api.service.IUserService;
import com.mg.api.vo.BaseResult;
import com.mg.api.vo.UserProfileVo;

@Controller
@ResponseBody
@RequestMapping("/v1/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ApiConfiguration apiConfig;
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private AliConfiguration aliConfig;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public BaseResult register(String username, String password, String code) {
		// 用户注册
		logger.debug("user register...");
		
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			// 检测邮箱支持
			if(!SystemSettings.EMAIL_REG_SUPPORT) {
				return new BaseResult(ErrorCode.ERR_SYS_REG_EMAIL_NOT_SUPPORT, "email register not support");
			}
			// 使用邮箱注册
			return userService.registerByEmail(username, password, code);
		} else if(StringUtil.isMobile(username)) {
			// 检测手机支持
			if(!SystemSettings.MOBIL_REG_SUPPORT) {
				return new BaseResult(ErrorCode.ERR_SYS_REG_MOBILE_NOT_SUPPORT, "mobile register not support");
			}
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
			
			// 初始值处理
			if(StringUtil.isEmpty(user.getNickname())) {
				user.setNickname(null == user.getMobile() ? user.getEmail() : user.getMobile());
			}
			if(StringUtil.isEmpty(user.getPortrait())) {
				user.setPortrait(SystemSettings.DEFAULT_PORTRAIT);
			}
			
			// 保存cookie
			try {
				CookieUtil.setCookie(response, SystemConstant.COOKIES_UID_NAME,  URLEncoder.encode(user.getNickname(), "utf-8"), apiConfig.getDomainUrl());
				CookieUtil.setCookie(response, SystemConstant.COOKIES_NICKNAME_NAME,  URLEncoder.encode(user.getNickname(), "utf-8"), apiConfig.getDomainUrl());
				CookieUtil.setCookie(response, SystemConstant.COOKIES_HEADIMG_NAME,  URLEncoder.encode(aliConfig.getOssBktImageUrl() + user.getPortrait(), "utf-8"), apiConfig.getDomainUrl());
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
		profile.setUpdateDate(DateUtil.current());
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
	public BaseResult uploadPortrait(MultipartFile portrait, HttpServletRequest request, HttpServletResponse response) {
		// 用户头像上传
		logger.debug("upload user portrait...");
		BaseResult result = null;
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// file check
		logger.debug("portrait check...");
		result = fileService.check(portrait, ResourceType.IMAGE);
		if(result.getCode() != ErrorCode.SUCCESS) {
			return result;
		}
		
		// temp dir
		logger.debug("get temp dir...");
		String destDir = request.getSession().getServletContext().getRealPath("/") + SystemSettings.TEMP_DIR + sessionInfo.getUid() + File.separator;
		String destFile = destDir + "portrait";
		if(!new File(destDir).isDirectory()) {
			// create temp dir
			logger.debug("create temp dir...");
			new File(destDir).mkdirs();
		}
		
		// save file
		OutputStream fileStream = null;
		try {
			logger.debug("save portrait to local...");
			fileStream = new FileOutputStream(new File(destFile));
			FileCopyUtils.copy(portrait.getInputStream(), fileStream);
			
			// upload to oss and update profile
			logger.debug("upload portrait to oss and update user profile...");
			result = userService.uploadPortrait(sessionInfo.getUid(), destFile);
			if(result.getCode() == ErrorCode.SUCCESS) {
				// 更新cookie
				try {
					CookieUtil.setCookie(response, SystemConstant.COOKIES_HEADIMG_NAME,  URLEncoder.encode((String)result.getData(), "utf-8"), apiConfig.getDomainUrl());
				} catch (Exception e) {
					logger.debug("set cookie fail.", e);
				}
			}
		} catch (Exception e) {
			logger.error("upload portrait fail!");
			return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "upload portrait fail!");
		} finally {
			if(null != fileStream) {
				try {
					fileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// cleanup temp file
			logger.debug("cleanup temp file...");
			File delFile = new File(destFile);
			if(delFile.exists()){
				delFile.delete();
			}
			
			File delFold = new File(destDir);
			if(delFold.isDirectory()) {
				delFold.delete();
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "verify", method = RequestMethod.POST)
	public BaseResult verifyUserExist(String username) {
		// 用户信息保存
		logger.debug("verify user exist...");
		
		// 检测邮箱或手机
		logger.debug("check username[" + username + "].");
		if(StringUtil.isEmail(username)) {
			if(userService.isEmailExist(username)) {
				// 用户已存在
				logger.debug("user already exist...");
				return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST, "User Already Exist.");
			}
		} else if(StringUtil.isMobile(username)) {
			if(userService.isMobileExist(username)) {
				// 用户已存在
				logger.debug("user already exist...");
				return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST, "User Already Exist.");
			}
		} else {
			// 请求参数错误
			logger.debug("request parameter error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "parameter invalid");
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}
}
