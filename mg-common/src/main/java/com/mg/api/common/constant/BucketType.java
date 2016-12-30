package com.mg.api.common.constant;

public enum BucketType {
	FILE(SystemConstant.RESOURCE_TYPE_FILE, "FILE", "file storage"),
	IMAGE(SystemConstant.RESOURCE_TYPE_IMAGE, "IMAGE", "picture storage"),
	AUDIO(SystemConstant.RESOURCE_TYPE_AUDIO, "AUDIO", "audio storage"),
	VIDEO(SystemConstant.RESOURCE_TYPE_VIDEO, "VIDEO", "video storage"),
	OTHER(SystemConstant.RESOURCE_TYPE_OTHER, "OTHER", "other storage");
	
	private int code;
	private String key;
	private String desc;
	
	private BucketType(int code, String key, String desc) {
		this.code = code;
		this.key = key;
		this.desc = desc;
	}

	public int getCode(){
		return code;
	}
	
	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
