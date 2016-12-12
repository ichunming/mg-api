package com.ichunming.mg.common.constant;

public enum BucketType {
	FILE("FILE", "file storage"),
	IMAGE("IMAGE", "picture storage"),
	AUDIO("AUDIO", "audio storage"),
	VIDEO("VIDEO", "video storage"),
	OTHER("OTHER", "other storage");
	
	private String key;
	private String desc;
	
	private BucketType(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
