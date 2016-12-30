package com.mg.api.service;

import java.util.List;

import com.mg.api.model.Resource;
import com.mg.api.vo.BaseResult;

public interface IResourceService {
	/**
	 * 保存资源信息
	 * @param resource
	 */
	public void save(Resource resource);
	
	/**
	 * 保存资源信息
	 * @param resources
	 */
	public void save(List<Resource> resources);
	
	/**
	 * 删除资源信息
	 * @param uid
	 * @param urls
	 * @param type
	 * @return
	 */
	public BaseResult delete(Long uid, List<String> urls, int type);
	
	/**
	 * 取得资源列表
	 * @param uid
	 * @param page
	 * @param filter
	 * @param type
	 * @return
	 */
	public BaseResult getList(Long uid, int page, int filter, int type);
	
	public BaseResult statistics(Long uid);
}
