/**
 * User Service
 * create by ming 2016/11/15
 * v0.1
 */
package com.mg.api.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.api.common.constant.BucketType;
import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.UserStatus;
import com.mg.api.common.constant.UserType;
import com.mg.api.common.util.DateUtil;
import com.mg.api.common.util.EncryptionUtil;
import com.mg.api.common.util.RandomUtil;
import com.mg.api.core.service.OssService;
import com.mg.api.dao.UserDao;
import com.mg.api.dao.UserProfileDao;
import com.mg.api.entity.UserView;
import com.mg.api.model.User;
import com.mg.api.model.UserProfile;
import com.mg.api.service.IFileService;
import com.mg.api.service.IUserService;
import com.mg.api.service.IVerifyService;
import com.mg.api.vo.BaseResult;
import com.mg.api.vo.UserProfileVo;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserProfileDao profileDao;
	
	@Autowired
	private IVerifyService verifyService;
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private OssService ossService;
	
	@Override
	public BaseResult registerByEmail(String email, String password, String code) {
		BaseResult result = null;
		// check验证码
		result = verifyService.verifyCode(email, UserType.EMAIL.getCode(), code);
		
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
			return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST, "User Already Exist");
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
		result = verifyService.verifyCode(mobile, UserType.MOBILE.getCode(), code);
		
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
			return new BaseResult(ErrorCode.ERR_USER_VALIDATE_EXIST, "User Already Exist");
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
			return new BaseResult(ErrorCode.ERR_USER_NOT_EXIST, "User Not Exist");
		}
		
		// 状态check
		logger.debug("check status...");
		if(user.getStatus() == UserStatus.Invalid.getCode()) {
			// 未认证
			logger.debug("unauthenticated...");
			return new BaseResult(ErrorCode.ERR_USER_UNAUTHEN, "User Unauth");
		} else if(user.getStatus() == UserStatus.Locked.getCode()) {
			// 被锁定
			logger.debug("locked...");
			return new BaseResult(ErrorCode.ERR_USER_LOCK, "User Been Locked");
		}/* else if(user.getStatus() == UserStatus.Deleted.getCode()) {
			// 被删除
			logger.debug("deleted...");
			return new BaseResult(ErrorCode.ERR_USER_DELETE, "User Been Deleted");
		}*/
		
		// 密码check
		logger.debug("check password...");
		if(!EncryptionUtil.match(password, user.getSalt(), user.getPassword())) {
			// 密码不一致
			logger.debug("password not match...");
			return new BaseResult(ErrorCode.ERR_USER_PASSWD_INVALID, "Password Not Match");
		}
		
		// Session信息
		//logger.debug("create session info...");
		//SessionInfo sessionInfo = user.toSessionInfo();
		return new BaseResult(ErrorCode.SUCCESS, user);
	}

	@Override
	public BaseResult getProfile(Long uid) {
		// 取得用户Profile
		logger.debug("get user profile...");
		UserProfileVo profileVo = new UserProfileVo();
		profileVo.fromView(userDao.getView(uid));
		return new BaseResult(ErrorCode.SUCCESS, profileVo);
	}
	
	@Override
	public void saveProfile(UserProfile profile) {
		// 保存用户信息
		logger.debug("save user profile...");
		profileDao.update(profile);
	}

	@Override
	public BaseResult uploadPortrait(Long uid, String file) {
		// 获取头像ID
		logger.debug("generate portrait id...");
		String key = fileService.genPortraitId(uid);
		
		// 上传头像到OSS
		logger.debug("upload portrait to oss...");
		String urlPath = ossService.post(BucketType.IMAGE.getKey(), key, file);
		
		// 更新用户信息
		logger.debug("update user profile...");
		UserProfile profile = new UserProfile();
		profile.setUid(uid);
		profile.setPortrait(key);
		profile.setUpdateDate(DateUtil.current());
		profileDao.update(profile);
		return new BaseResult(ErrorCode.SUCCESS, urlPath);
	}

	@Override
	public boolean isEmailExist(String email) {
		if(null == userDao.getByEmail(email)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isMobileExist(String mobile) {
		if(null == userDao.getByMobile(mobile)) {
			return false;
		}
		return true;
	}

	@Override
	public BaseResult resetPwd(Long uid, String oldPwd, String newPwd) {
		// 取得用户信息
		logger.debug("get user...");
		User user = userDao.select(uid);
		
		// 原始密码check
		logger.debug("check password...");
		if(null == user || !EncryptionUtil.match(oldPwd, user.getSalt(), user.getPassword())) {
			// 密码不一致
			logger.debug("password not match...");
			return new BaseResult(ErrorCode.ERR_USER_PASSWD_INVALID, "Password Not Match");
		}

		// 生成密码信息
		logger.debug("create new user info...");
		User newUser = registerUser(newPwd);
		user.setSalt(newUser.getSalt());
		user.setPassword(newUser.getPassword());
		user.setUpdateDate(DateUtil.current());
		
		// 更新用户信息
		logger.debug("update user...");
		userDao.update(user);
		
		return new BaseResult(ErrorCode.SUCCESS);
	}
	
	private User registerUser(String password) {
		// 构造随机数作为salt
		logger.debug("generate salt...");
        String salt = RandomUtil.genCharacterString(16);
        // 默认算法加密
        logger.debug("encrypt password...");
        String enPwd = EncryptionUtil.encrypt(password, salt);
        // 创建用户
        logger.debug("create user...");
        Date current = DateUtil.current();
        User user = new User();
        user.setSalt(salt);
        user.setPassword(enPwd);
        user.setStatus(UserStatus.Active.getCode());
        user.setCreateDate(current);
        user.setUpdateDate(current);
        
        return user;
	}
	
	private void createProfile(Long uid) {
		UserProfile profile = new UserProfile();
		Date current = DateUtil.current();
        profile.setUid(uid);
        profile.setCreateDate(current);
        profile.setUpdateDate(current);
        
        profileDao.insert(profile);
	}
}
