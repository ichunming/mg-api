/**
 * User Service Interface
 * create by ming 2016/11/15
 * v0.1
 */
package com.ichunming.mg.service;

import com.ichunming.mg.entity.vo.BaseResult;

public interface IUserService {

	/**
	 * 用户注册--邮箱
	 * @param email
	 * @param password
	 * @return
	 */
	public BaseResult registerByEmail(String email, String password, String code);
	
	/**
	 * 用户注册--手机
	 * @param mobile
	 * @param password
	 * @return
	 */
	public BaseResult registerByMobile(String mobile, String password, String code);
	
	/**
	 * 用户登入
	 * @param username
	 * @param password
	 * @return
	 */
	public BaseResult login(String username, String password);

	/**
	 * 用户信息保存
	 * @param email
	 * @param mobile
	 * @param nickname
	 * @param realname
	 * @return
	 */
	public BaseResult saveProfile(String email, String mobile, String nickname, String realname);
	
	/**
	 * 用户头像上传
	 * @param portrait
	 * @return
	 */
	public BaseResult uploadPortrait(String portrait);
	
	/**
	 * 邮箱是否存在check
	 * @param email
	 * @return
	 */
	public boolean isEmailExist(String email);
	
	/**
	 * 手机是否存在check
	 * @param mobile
	 * @return
	 */
	public boolean isMobileExist(String mobile);
	
	/**
	 * 邮箱发送认证code
	 * @param email
	 * @return
	 */
	public BaseResult sendCodeByEmail(String email);
	
	/**
	 * 手机发送认证code
	 * @param mobile
	 * @return
	 */
	public BaseResult sendCodeByMobile(String mobile);
}
