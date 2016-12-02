/**
 * User Service
 * create by ming 2016/11/15
 * v0.1
 */
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
import com.ichunming.mg.common.constant.UserStatus;
import com.ichunming.mg.common.constant.UserType;
import com.ichunming.mg.common.util.DateUtil;
import com.ichunming.mg.common.util.EncryptionUtil;
import com.ichunming.mg.common.util.RandomUtil;
import com.ichunming.mg.core.service.EmailService;
import com.ichunming.mg.core.service.SmsService;
import com.ichunming.mg.dao.UserDao;
import com.ichunming.mg.dao.UserProfileDao;
import com.ichunming.mg.dao.VerifyInfoDao;
import com.ichunming.mg.entity.SessionInfo;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.model.User;
import com.ichunming.mg.model.UserProfile;
import com.ichunming.mg.model.UserView;
import com.ichunming.mg.model.VerifyInfo;
import com.ichunming.mg.service.ICommonService;
import com.ichunming.mg.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserProfileDao profileDao;
	
	@Autowired
	private ICommonService commonService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private VerifyInfoDao verifyDao;
	
	@Override
	public BaseResult registerByEmail(String email, String password, String code) {
		BaseResult result = null;
		// check验证码
		result = commonService.verifyCode(email, UserType.EMAIL.getCode(), code);
		
		if(result.getCode().longValue() == ErrorCode.ERR_USER_VERIFY_CODE_INVALID.longValue()) {
			// 验证码无效
			logger.debug("verify code invalid.");
			return result;
		} else if(result.getCode().longValue() == ErrorCode.ERR_USER_VERIFY_CODE_OVERDUE.longValue()) {
			// 验证码过期
			logger.debug("verify code overdue.");
			return result;
		}
		
		// 检测用户是否存在
		if(isEmailExist(email)) {
			logger.debug("username already exist.");
			return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST);
		}
		
		// 邮箱注册
		logger.debug("register by email[" + email + "]");
		User user = registerUser(password);
        user.setEmail(email);
        userDao.insert(user);
        // 创建用户信息
        logger.debug("create profile...");
        createProfile(user.getId());
        
		return new BaseResult(ErrorCode.SUCCESS);
	}

	@Override
	public BaseResult registerByMobile(String mobile, String password, String code) {
		BaseResult result = null;
		// check验证码
		result = commonService.verifyCode(mobile, UserType.MOBILE.getCode(), code);
		
		if(result.getCode().longValue() == ErrorCode.ERR_USER_VERIFY_CODE_INVALID.longValue()) {
			// 验证码无效
			logger.debug("verify code invalid.");
			return result;
		} else if(result.getCode().longValue() == ErrorCode.ERR_USER_VERIFY_CODE_OVERDUE.longValue()) {
			// 验证码过期
			logger.debug("verify code overdue.");
			return result;
		}
		
		// 检测用户是否存在-double check
		if(isMobileExist(mobile)) {
			logger.debug("username already exist.");
			return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST);
		}
		
		// 手机注册
		logger.debug("register by mobile[" + mobile + "]");
		User user = registerUser(password);
        user.setMobile(mobile);
        userDao.insert(user);
        // 创建用户信息
        logger.debug("create profile...");
        createProfile(user.getId());
        
		return result;
	}

	@Override
	public BaseResult login(String username, String password) {
		// 取得用户View
		logger.debug("get user by username[" + username + "]");
		UserView user = userDao.getViewByUsername(username);
		
		// 检测用户是否存在
		if(null == user) {
			logger.debug("username not exist.");
			return new BaseResult(ErrorCode.ERR_USER_NOT_EXIST);
		}
		
		// 状态check
		logger.debug("check status...");
		if(user.getStatus() == UserStatus.Invalid.getCode()) {
			// 未认证
			logger.debug("unauthenticated...");
			return new BaseResult(ErrorCode.ERR_USER_UNAUTHEN);
		} else if(user.getStatus() == UserStatus.Locked.getCode()) {
			// 被锁定
			logger.debug("locked...");
			return new BaseResult(ErrorCode.ERR_USER_LOCK);
		} else if(user.getStatus() == UserStatus.Deleted.getCode()) {
			// 被删除
			logger.debug("deleted...");
			return new BaseResult(ErrorCode.ERR_USER_DELETE);
		}
		
		// 密码check
		logger.debug("check password...");
		if(!EncryptionUtil.match(password, user.getSalt(), user.getPassword())) {
			// 密码不一致
			logger.debug("password not match...");
			return new BaseResult(ErrorCode.ERR_USER_PASSWD_INVALID);
		}
		
		// Session信息
		logger.debug("create session info...");
		SessionInfo sessionInfo = user.toSessionInfo();
		return new BaseResult(ErrorCode.SUCCESS, sessionInfo);
	}

	@Override
	public BaseResult saveProfile(String email, String mobile, String nickname, String realname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult uploadPortrait(String portrait) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmailExist(String email) {
		if(userDao.getCntByEmail(email) == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isMobileExist(String mobile) {
		if(userDao.getCntByMobile(mobile) == 0) {
			return false;
		}
		return true;
	}
	
	private User registerUser(String password) {
		// 构造随机数作为salt
		logger.debug("generate salt...");
        String salt = RandomUtil.genCharacterString(16);
        // 默认算法加密
        logger.debug("encrypt password...");
        String enPwd = EncryptionUtil.encrypt(password, salt);
        // 创建用户
        logger.debug("create User...");
        User user = new User();
        user.setSalt(salt);
        user.setPassword(enPwd);
        user.setStatus(UserStatus.Active.getCode());
        
        return user;
	}
	
	private void createProfile(Long uid) {
		UserProfile profile = new UserProfile();
        profile.setUid(uid);
        profileDao.insert(profile);
	}

	@Override
	public BaseResult sendCodeByEmail(String email) {
		// generate validate code
		logger.debug("generate validate code...");
		String code = RandomUtil.genDigitalString(6);
		
		// send validate code
		logger.debug("send validate code...");
		if(!emailService.send("帐号验证", "验证码：" + code + "，有效时间3分钟", email)) {
			return new BaseResult(ErrorCode.ERR_SVR_EMAIL_SEND_FAIL);
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
		// generate validate code
		logger.debug("generate validate code...");
		String code = RandomUtil.genDigitalString(6);
		
		// send validate code
		logger.debug("send validate code...");
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", code);
		if(!smsService.sendValidation(Arrays.asList(mobile), param)) {
			return new BaseResult(ErrorCode.ERR_SVR_SMS_FAIL);
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
}
