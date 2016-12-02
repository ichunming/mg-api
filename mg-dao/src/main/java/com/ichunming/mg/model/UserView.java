package com.ichunming.mg.model;

import com.ichunming.mg.entity.SessionInfo;

public class UserView {

	private Long id;
	
	private String password;

	private String salt;
	
	private String email;

    private String mobile;

    private Integer status;
    
    private String nickname;

    private String portrait;

    private String realName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public SessionInfo toSessionInfo() {
		SessionInfo info = new SessionInfo();
		info.setEmail(this.email);
		info.setUid(this.id);
		info.setMobile(this.mobile);
		info.setNickname(this.nickname);
		info.setPortrait(this.portrait);
		info.setRealName(this.realName);
		
		return info;
	}
}
