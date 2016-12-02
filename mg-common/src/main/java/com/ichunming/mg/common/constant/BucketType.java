package com.ichunming.mg.common.constant;

public enum BucketType {
	FILE(1, "FILE"),
	PIC(2, "PICTURE"),
	AUDIO(3, "AUDIO"),
	VIDEO(4, "VIDEO"),
	OTHER(5, "OTHER");
	
	private int code;
	private String key;
	
	private BucketType(int code, String key) {
		this.code = code;
		this.key = key;
	}

	public int getCode() {
		return code;
	}
	
	public String getKey() {
		return key;
	}
}
