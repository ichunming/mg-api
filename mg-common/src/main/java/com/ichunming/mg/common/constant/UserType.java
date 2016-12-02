package com.ichunming.mg.common.constant;

public enum UserType {
	MOBILE(0, "mobile user"),
	EMAIL(1, "email user");
	
	private int code;
	private String desc;
	
	private UserType(int code, String desc) {
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
