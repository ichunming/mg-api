package com.mg.api.common.util.helper;

public class SessionInfo {
	
	private Long uid;

    public SessionInfo() {}
    
    public SessionInfo(Long uid) {
    	this.uid = uid;
    }
    
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
}
