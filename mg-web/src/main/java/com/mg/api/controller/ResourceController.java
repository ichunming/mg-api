/**
 * Resource Controller
 * ming 2016/12/12
 */
package com.mg.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.SystemSettings;
import com.mg.api.common.util.DateUtil;
import com.mg.api.common.util.SessionUtil;
import com.mg.api.common.util.StringUtil;
import com.mg.api.common.util.helper.SessionInfo;
import com.mg.api.model.Resource;
import com.mg.api.service.IFileService;
import com.mg.api.service.IResourceService;
import com.mg.api.vo.BaseResult;

@Controller
@ResponseBody
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private IResourceService resourceService;
	
	/**
	 * 资源上传
	 * @param file
	 * @param type (1:图片 2:音频 3:视频)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public BaseResult upload(MultipartFile file, int type, HttpServletRequest request) {
		// 资源上传
		logger.debug("upload resource request");
		BaseResult result = null;
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// 文件 check
		logger.debug("portrait check...");
		result = fileService.check(file, fileService.getRscType(type));
		if(result.getCode() != ErrorCode.SUCCESS) {
			return result;
		}
		
		// 保存文件
		logger.debug("save file...");
		String tempDir = request.getSession().getServletContext().getRealPath("/") + SystemSettings.TEMP_DIR;
		String key = fileService.genResourceId();
		if(StringUtil.isEmpty(key)) {
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "generate resource id fail");
		}
		String urlPath = fileService.saveToOss(tempDir, sessionInfo.getUid(), file, fileService.getBktType(type), key);
		
		if(StringUtil.isEmpty(urlPath)) {
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "file upload fail");
		}
		
		// 更新资源信息
		logger.debug("save resource info to db...");
		Resource resource = new Resource();
		resource.setUid(sessionInfo.getUid());
		resource.setId(key);
		resource.setType(type);
		resource.setUseCnt(0);
		resource.setSize(file.getSize());
		resource.setCreateDate(DateUtil.current());
		resourceService.save(resource);
		
		return new BaseResult(ErrorCode.SUCCESS, urlPath);
	}
	
	/**
	 * 资源批量上传
	 * @param files
	 * @param type (1:图片 2:音频 3:视频)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "upload/batch", method = RequestMethod.POST)
	public BaseResult batchUpload(List<MultipartFile> files, int type, HttpServletRequest request) {
		// 资源批量上传
		logger.debug("upload batch resource request");
		BaseResult result = null;
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// 文件 check
		logger.debug("portrait check...");
		result = fileService.check(files, fileService.getRscType(type));
		if(result.getCode() != ErrorCode.SUCCESS) {
			return result;
		}
		
		// 保存文件
		logger.debug("save file...");
		String tempDir = request.getSession().getServletContext().getRealPath("/") + SystemSettings.TEMP_DIR;
		List<String> keys = new ArrayList<String>();
		fileService.genResourceIds(keys, files.size());
		if(keys.size() != files.size()) {
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "generate resource ids fail");
		}
				
		
		List<String> urlPaths = fileService.saveToOss(tempDir, sessionInfo.getUid(), files, fileService.getBktType(type), keys);
		
		if(null == urlPaths) {
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "file upload fail");
		}
		
		// 更新资源信息
		logger.debug("save resource info to db...");
		List<Resource> resources = new ArrayList<Resource>();
		Resource resource = null;
		Date current = DateUtil.current();
		for(int i = 0; i < files.size(); i++) {
			resource = new Resource();
			resource.setUid(sessionInfo.getUid());
			resource.setId(keys.get(i));
			resource.setType(type);
			resource.setUseCnt(0);
			resource.setSize(files.get(i).getSize());
			resource.setCreateDate(current);
			
			resources.add(resource);
		}
		
		resourceService.save(resources);
		
		return new BaseResult(ErrorCode.SUCCESS, urlPaths);
	}

	/**
	 * 资源删除
	 * @param urls
	 * @param type (1:图片 2:音频 3:视频)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public BaseResult delete(List<String> urls, int type, HttpServletRequest request) {
		// 资源删除
		logger.debug("delete resource request");
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// check type
		if(null == fileService.getRscType(type)) {
			logger.debug("resource type invalid");
			return new BaseResult(ErrorCode.ERR_RSC_TYPE_NOT_MATCH);
		}
		
		return resourceService.delete(sessionInfo.getUid(), urls, type);
	}
	
	/**
	 * 取得资源列表
	 * @param type (1:图片 2:音频 3:视频)
	 * @param page
	 * @param filter (1:时间  2:使用次数)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "get/list", method = RequestMethod.POST)
	public BaseResult getList(int type, @RequestParam(required = false, defaultValue = "1") int page, 
			@RequestParam(required = false, defaultValue = "1") int filter, HttpServletRequest request) {
		// 取得资源列表
		logger.debug("get resource list request");
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		// check type
		if(null == fileService.getRscType(type)) {
			logger.debug("resource type invalid");
			return new BaseResult(ErrorCode.ERR_RSC_TYPE_NOT_MATCH);
		}
		
		return resourceService.getList(sessionInfo.getUid(), page, filter, type);
	}

	/**
	 * 资源统计
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.POST)
	public BaseResult statistics(HttpServletRequest request) {
		// 统计资源信息
		logger.debug("statistics resource info request");
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		
		return resourceService.statistics(sessionInfo.getUid());
	}
}
