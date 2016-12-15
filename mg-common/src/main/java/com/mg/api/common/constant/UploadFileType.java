package com.mg.api.common.constant;

import java.util.List;

import com.mg.api.common.util.StringUtil;

public enum UploadFileType {
	IMAGE("image/jpeg;image/gif;image/jpeg;image/png", 1024 * 1024L, "图片"), // 1M
	AUDIO("audio/mp3;audio/wav;audio/x-ms-wma", 20 * 1024 * 1024L, "音频"), // 20M
	VIDEO("video/avi;video/mpeg4;", 200 * 1024 * 1024L, "视频"); //200M
	
	private List<String> types;
	private Long maxSize;
	private String desc;
	
	private UploadFileType(String types, Long maxSize, String desc) {
		this.types = StringUtil.toList(types, ";");
		this.maxSize = maxSize;
		this.desc = desc;
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
