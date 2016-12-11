package com.ichunming.mg.entity;

public class SessionInfo {
	
	private Long uid;
	
	private String email;

    private String mobile;

    public SessionInfo() {}
    
    public SessionInfo(Long uid, String email, String mobile) {
    	this.uid = uid;
    	this.email = email;
    	this.mobile = mobile;
    }
    
	public void fromView(UserView view) {
		this.uid = view.getId();
		this.email = view.getEmail();
	    this.mobile = view.getMobile();
	}
    
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
}
