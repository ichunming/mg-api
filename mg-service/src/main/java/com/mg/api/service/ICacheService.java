/**
 * @author ming
 * @date 2017年1月25日 下午5:15:01
 */
package com.mg.api.service;

import com.mg.api.common.util.helper.UserInfo;

public interface ICacheService {
	public void saveUserInfo(Long uid, UserInfo userInfo);
	
	public UserInfo getUserInfo(Long uid);
}