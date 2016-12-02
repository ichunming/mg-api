package com.ichunming.mg.entity;

public class SessionInfo {
	
	private Long uid;
	
	private String email;

    private String mobile;

    private String nickname;

    private String portrait;

    private String realName;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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
	
	public SessionInfo() {}
	
	public SessionInfo(Long uid, String email, String mobile) {
		this.uid = uid;
		this.email = email;
		this.mobile = mobile;
	}
}
