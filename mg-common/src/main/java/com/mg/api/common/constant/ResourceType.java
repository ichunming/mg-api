package com.mg.api.common.constant;

import java.util.List;

import com.mg.api.common.util.StringUtil;

public enum ResourceType {
	IMAGE(SystemConstant.RESOURCE_TYPE_IMAGE, "application/x-bmp;application/x-jpg;image/jpeg;image/gif;image/png", 5 * 1024 * 1024L, "图片"), // 5M
	AUDIO(SystemConstant.RESOURCE_TYPE_AUDIO, "audio/mp3;audio/wav;audio/x-ms-wma", 50 * 1024 * 1024L, "音频"), // 50M
	VIDEO(SystemConstant.RESOURCE_TYPE_VIDEO, "video/avi;video/mpeg4;", 500 * 1024 * 1024L, "视频"); //500M
	
	private int code;
	private List<String> types;
	private Long maxSize;
	private String desc;
	
	private ResourceType(int code, String types, Long maxSize, String desc) {
		this.code = code;
		this.types = StringUtil.toList(types, ";");
		this.maxSize = maxSize;
		this.desc = desc;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public List<String> getTypes() {
		return this.types;
	}
	
	public Long getMaxSize() {
		return this.maxSize;
	}
	
	public String toString() {
		return this.desc;
	}
}
