package com.mg.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mg.api.model.Resource;
import com.mg.api.vo.ResourceStatsVo;
import com.mg.api.vo.ResourceVo;

public interface ResourceDao extends GenericDao<Resource, String> {
	public void batchInsert(List<Resource> resources);
	
	public List<Resource> getByIds(List<String> ids);
	
	public void deleteByIds(List<String> ids);
	
	public List<ResourceVo> getByTimeFilter(@Param("uid") Long uid, @Param("type") int type);
	
	public List<ResourceVo> getByCntFilter(@Param("uid") Long uid, @Param("type") int type);
	
	public List<ResourceStatsVo> statistics(Long uid);
}