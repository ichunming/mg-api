package com.mg.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.ResourceType;
import com.mg.api.vo.BaseResult;

public interface IFileService {

	// chec文件类型,大小
	public BaseResult check(MultipartFile file, ResourceType type);

	// 生成头像ID
	public String genPortraitId(Long uid);
	
	// 生成资源ID
	public String genResourceId();
}
