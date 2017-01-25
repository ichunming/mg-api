/**
 * UserController
 * ming 2016/11/15
 */
package com.mg.api.controller;

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

import com.mg.api.common.constant.BucketType;
import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.ResourceType;
import com.mg.api.common.constant.SystemConstant;
import com.mg.api.common.constant.SystemSettings;
import com.mg.api.common.util.CookieUtil;
import com.mg.api.common.util.DateUtil;
import com.mg.api.common.util.SessionUtil;
import com.mg.api.common.util.StringUtil;
import com.mg.api.common.util.helper.SessionInfo;
import com.mg.api.core.configuration.AliConfiguration;
import com.mg.api.core.configuration.ApiConfiguration;
import com.mg.api.core.helper.LocationManager;
import com.mg.api.entity.UserView;
import com.mg.api.model.UserProfile;
import com.mg.api.service.IFileService;
import com.mg.api.service.IUserService;
import com.mg.api.vo.BaseResult;
import com.mg.api.vo.UserProfileVo;

@Controller
@ResponseBody
@RequestMapping("/user")
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
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public BaseResult register(String username, String password, String code) {
		// 用户注册
		logger.debug("user register...");
		
		logger.debug("check username[" + username + "].");
		// check密码格式
		if(!StringUtil.isPassword(password)) {
			// 请求参数错误
			logger.debug("password format error.");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "password invalid");
		}
		// 检测邮箱或手机
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
	
	/**
	 * 用户登入
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
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
			SessionInfo sessionInfo = new SessionInfo(user.getId());
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
	
	/**
	 * 重置密码
	 * @param oldPwd
	 * @param newPwd
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 用户信息取得
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "profile/get", method = RequestMethod.POST)
	public BaseResult getProfile(HttpServletRequest request) {
		// 用户信息取得
		logger.debug("get user profile...");

		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		return userService.getProfile(sessionInfo.getUid());
	}
	
	/**
	 * 用户信息保存
	 * @param profileVo
	 * @param request
	 * @param response
	 * @return
	 */
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
		
		// location check
		logger.debug("check location info...");
		if(null != profileVo.getProvinceId() && null == LocationManager.get(profileVo.getProvinceId())) {
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "province id not exist");
		}
		if(null != profileVo.getCityId() && null == LocationManager.get(profileVo.getCityId())) {
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "city id not exist");
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

	/**
	 * 用户头像上传
	 * @param portrait
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "profile/portrait/upload", method = RequestMethod.POST)
	public BaseResult uploadPortrait(MultipartFile portrait, HttpServletRequest request, HttpServletResponse response) {
		// 用户头像上传
		logger.debug("upload user portrait...");
		BaseResult result = null;
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// 文件 check
		logger.debug("portrait check...");
		result = fileService.check(portrait, ResourceType.IMAGE);
		if(result.getCode() != ErrorCode.SUCCESS) {
			return result;
		}
		
		// 保存文件
		logger.debug("save file...");
		String tempDir = request.getSession().getServletContext().getRealPath("/") + SystemSettings.TEMP_DIR;
		String key = fileService.genPortraitId(sessionInfo.getUid());
		String urlPath = fileService.saveToOss(tempDir, sessionInfo.getUid(), portrait, BucketType.IMAGE, key);
		
		if(StringUtil.isEmpty(urlPath)) {
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "file upload fail");
		}
		
		// 更新用户信息
		logger.debug("update user profile...");
		UserProfile profile = new UserProfile();
		profile.setUid(sessionInfo.getUid());
		profile.setPortrait(key);
		profile.setUpdateDate(DateUtil.current());
		userService.saveProfile(profile);
		
		// 更新 cookie
		try {
			CookieUtil.setCookie(response, SystemConstant.COOKIES_HEADIMG_NAME,  URLEncoder.encode(urlPath, "utf-8"), apiConfig.getDomainUrl());
		} catch (Exception e) {
			logger.debug("set cookie fail.", e);
		}
		
		return new BaseResult(ErrorCode.SUCCESS, urlPath);
	}
	
	/**
	 * 用户存在check
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "verify", method = RequestMethod.POST)
	public BaseResult verifyUserExist(String username) {
		// 用户存在check
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
