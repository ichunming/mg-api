/**
 * @author ming
 * @date 2017年1月25日 下午5:04:41
 */
package com.mg.api.common.util.helper;

public class UserInfo {
	private String mobile;
	
	private String email;

	public UserInfo() {};

	public UserInfo(String mobile, String email) {
		this.mobile = mobile;
		this.email = email;
	};
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}