/**
 * Resource Service Impl
 * ming 2016/12/27
 */
package com.mg.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.SystemConstant;
import com.mg.api.common.util.StringUtil;
import com.mg.api.core.service.OssService;
import com.mg.api.dao.ResourceDao;
import com.mg.api.entity.MgPageInfo;
import com.mg.api.model.Resource;
import com.mg.api.service.IFileService;
import com.mg.api.service.IResourceService;
import com.mg.api.vo.BaseResult;
import com.mg.api.vo.ResourceStatsVo;
import com.mg.api.vo.ResourceVo;

@Service
public class ResourceServiceImpl implements IResourceService {
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private OssService ossService;
	
	@Autowired
	private IFileService fileService;
	
	@Override
	public void save(Resource resource) {
		logger.debug("save resource...");
		resourceDao.insert(resource);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IResourceService#save(java.util.List)
	 */
	@Override
	public void save(List<Resource> resources) {
		logger.debug("save resources...");
		resourceDao.batchInsert(resources);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IResourceService#delete(java.lang.Long, java.util.List, int)
	 */
	@Override
	public BaseResult delete(Long uid, List<String> urls, int type) {
		// 取得资源key
		logger.debug("get resource key...");
		List<String> keys = ossService.convertUrls2Keys(urls);
		if(null == keys || keys.size() == 0) {
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "invalid parameter");
		}
		
		// 取得资源信息
		logger.debug("get resource infos...");
		List<Resource> resources = resourceDao.getByIds(keys);
		if(resources.size() != urls.size()) {
			logger.debug("invalid ids[" + StringUtil.toString(keys, ",") + "]");
			return new BaseResult(ErrorCode.ERR_RSC_ID_INVALID, "contain invalid resource id");
		}
		
		// check 资源信息
		logger.debug("check resource info...");
		for(Resource resource : resources) {
			if(resource.getUid() != uid) {
				logger.debug("user has no authority");
				return new BaseResult(ErrorCode.ERR_RSC_NO_AUTH, "user has no authority");
			}
			
			if(resource.getType() != type) {
				logger.debug("resource type not match");
				return new BaseResult(ErrorCode.ERR_RSC_TYPE_NOT_MATCH, "resource type not match");
			}
		}
		
		// 删除oss资源
		logger.debug("delete resource from oss...");
		ossService.delete(fileService.getBktType(type).getKey(), keys);
		
		// 删除资源信息
		logger.debug("delete resource info...");
		resourceDao.deleteByIds(keys);
		
		return new BaseResult(ErrorCode.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IResourceService#getList(java.lang.Long, int, int)
	 */
	@Override
	public BaseResult getList(Long uid, int page, int filter, int type) {
		// 分页
		logger.debug("set page...");
		PageHelper.startPage(page, SystemConstant.PAGE_SIZE_RSC);
		
		List<ResourceVo> resources = null;
		
		// 取得数据
		logger.debug("get resource...");
		switch(filter) {
		case 1:
			// 时间
			logger.debug("filter by time...");
			resources = resourceDao.getByTimeFilter(uid, type);
			break;
		case 2:
			// 使用数
			logger.debug("filter by use cnt...");
			resources = resourceDao.getByCntFilter(uid, type);
			break;
		default:
			return new BaseResult(ErrorCode.ERR_RSC_FILTER_NOT_SUPPORT, "filter not support");
		}
		
		return new BaseResult(ErrorCode.SUCCESS, new MgPageInfo<ResourceVo>(resources));
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IResourceService#statistics(java.lang.Long)
	 */
	@Override
	public BaseResult statistics(Long uid) {
		// 统计资源信息
		logger.debug("statistics resource info...");
		List<ResourceStatsVo> reStatsVos = resourceDao.statistics(uid);
		
		boolean hasImg = false;
		boolean hasAudio = false;
		boolean hasVideo = false;
		
		// check结果集
		logger.debug("check result...");
		for(ResourceStatsVo reStatsVo : reStatsVos) {
			switch(reStatsVo.getType()) {
			case SystemConstant.RESOURCE_TYPE_IMAGE:
				hasImg = true;
				break;
			case SystemConstant.RESOURCE_TYPE_AUDIO:
				hasAudio = true;
				break;
			case SystemConstant.RESOURCE_TYPE_VIDEO:
				hasVideo = true;
				break;
			default :
				break;
			}
		}
		
		if(!hasImg) {
			reStatsVos.add(new ResourceStatsVo(SystemConstant.RESOURCE_TYPE_IMAGE, 0, 0L));
		}
		
		if(!hasAudio) {
			reStatsVos.add(new ResourceStatsVo(SystemConstant.RESOURCE_TYPE_AUDIO, 0, 0L));
		}
		
		if(!hasVideo) {
			reStatsVos.add(new ResourceStatsVo(SystemConstant.RESOURCE_TYPE_VIDEO, 0, 0L));
		}
		
		return new BaseResult(ErrorCode.SUCCESS, reStatsVos);
	}
}