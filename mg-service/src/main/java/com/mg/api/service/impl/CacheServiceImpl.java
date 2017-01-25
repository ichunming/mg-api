/**
 * @author ming
 * @date 2017年1月25日 下午5:17:27
 */
package com.mg.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.api.common.constant.SystemConstant;
import com.mg.api.common.util.helper.UserInfo;
import com.mg.api.core.cache.ICacheManager;
import com.mg.api.dao.UserDao;
import com.mg.api.model.User;
import com.mg.api.service.ICacheService;

@Service
public class CacheServiceImpl implements ICacheService {

	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
	
	@Autowired
	private ICacheManager cacheManager;
	
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.mg.api.service.ICacheService#saveUserInfo()
	 */
	@Override
	public void saveUserInfo(Long uid, UserInfo userInfo) {
		try {
			cacheManager.put(SystemConstant.CACHE_REGIONI_USER_INFO, uid.toString(), userInfo);
		} catch (Exception e) {
			logger.error("Caught an exception when save user info into memcache.");
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.ICacheService#getUserInfo()
	 */
	@Override
	public UserInfo getUserInfo(Long uid) {
		UserInfo userInfo = null;
		
		try {
			// get user info from cache
			logger.debug("get user info from cache...");
			userInfo = (UserInfo) cacheManager.get(SystemConstant.CACHE_REGIONI_USER_INFO, uid.toString());
		} catch (Exception e) {
			logger.error("Caught an exception when save user info into memcache.");
		}
		
		if(null == userInfo) {
			// get user info from cache failed
			logger.debug("get user info from cache failed.");
			logger.debug("get user info from database...");
			User user = userDao.select(uid);
			
			userInfo = new UserInfo(user.getMobile(), user.getEmail());
		}
		
		return userInfo;
	}
}