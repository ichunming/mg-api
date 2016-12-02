package com.ichunming.mg.common.constant;

public enum UserStatus {

	Invalid(0, "未认证"),
	Active(1, "可用"),
	Locked(2, "锁定"),
	Deleted(3, "删除");
	
	private int code;
	private String desc;
	
	private UserStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String toString() {
		return desc;
	}
}
