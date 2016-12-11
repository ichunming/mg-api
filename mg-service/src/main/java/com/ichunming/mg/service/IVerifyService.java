package com.ichunming.mg.service;

import com.ichunming.mg.vo.BaseResult;

public interface IVerifyService {
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
	
	/**
	 * 验证
	 * @param receiver
	 * @param type 0:Mobile, 1:Email
	 * @param code
	 * @return
	 */
	public BaseResult verifyCode(String receiver, int type, String code);
}
