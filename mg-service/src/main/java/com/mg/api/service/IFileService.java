package com.mg.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.BucketType;
import com.mg.api.common.constant.ResourceType;
import com.mg.api.vo.BaseResult;

public interface IFileService {

	/**
	 * chec文件类型,大小
	 * @param file
	 * @param type
	 * @return
	 */
	public BaseResult check(MultipartFile file, ResourceType type);
		
	/**
	 * chec文件类型,大小
	 * @param files
	 * @param type
	 * @return
	 */
	public BaseResult check(List<MultipartFile> files, ResourceType type);
	
	/**
	 * 生成头像ID
	 * @param uid
	 * @return
	 */
	public String genPortraitId(Long uid);
	
	/**
	 * 生成资源ID
	 * @return
	 */
	public String genResourceId();
	
	/**
	 * 生成资源ID
	 * @param ids
	 * @param size
	 * @return
	 */
	public void genResourceIds(List<String> ids, int size);
	
	/**
	 * 上传资源
	 * @param tempDir
	 * @param uid
	 * @param file
	 * @param bucket
	 * @param key
	 * @return
	 */
	public String saveToOss(String tempDir, Long uid, MultipartFile file, BucketType bucket, String key);

	/**
	 * 批量上传资源
	 * @param tempDir
	 * @param uid
	 * @param files
	 * @param bucket
	 * @param keys
	 * @return
	 */
	public List<String> saveToOss(String tempDir, Long uid, List<MultipartFile> files, BucketType bucket, List<String> keys);
	
	/**
	 * 取得资源类型
	 * @param type
	 * @return
	 */
	public ResourceType getRscType(int type);
	
	/**
	 * 取得Bucket类型
	 * @param type
	 * @return
	 */
	public BucketType getBktType(int type);
}
