/**
 * File Service Impl
 * ming 2016/12/21
 */
package com.mg.api.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.BucketType;
import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.ResourceType;
import com.mg.api.common.constant.SystemConstant;
import com.mg.api.common.util.RandomUtil;
import com.mg.api.core.service.OssService;
import com.mg.api.dao.ResourceDao;
import com.mg.api.model.Resource;
import com.mg.api.service.IFileService;
import com.mg.api.vo.BaseResult;

@Service
public class FileServiceImpl implements IFileService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	 private OssService ossService;
	
	@Override
	public BaseResult check(MultipartFile file, ResourceType type) {
		// file empty check
		if(file.isEmpty()) {
			logger.debug("empty file!");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "empty file");
		}
		
		// file type check
		if(null != type && !type.getTypes().contains(file.getContentType())) {
			logger.info("file type incorrect!");
			return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_TYPE_LIMIT, "incorrect file type");
		}
		
		// file size check
		if(file.getSize() > type.getMaxSize()) {
			logger.info("file is oversize!");
			return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_SIZE_LIMIT, "file oversize");
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IFileService#check(java.util.List, com.mg.api.common.constant.ResourceType)
	 */
	@Override
	public BaseResult check(List<MultipartFile> files, ResourceType type) {
		// check files
		logger.debug("check files...");
		BaseResult result = null;
		switch(type.getCode()) {
		case SystemConstant.RESOURCE_TYPE_IMAGE:
			if(files.size() > SystemConstant.RESOURCE_IMAGE_UPLOAD_MAX_NUM) {
				return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_NUM_LIMIT, "file overage");
			}
			break;
		case SystemConstant.RESOURCE_TYPE_AUDIO:
			if(files.size() > SystemConstant.RESOURCE_AUDIO_UPLOAD_MAX_NUM) {
				return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_NUM_LIMIT, "file overage");
			}
			break;
		case SystemConstant.RESOURCE_TYPE_VIDEO:
			if(files.size() > SystemConstant.RESOURCE_VIDEO_UPLOAD_MAX_NUM) {
				return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_NUM_LIMIT, "file overage");
			}
			break;
		default:
			return new BaseResult(ErrorCode.ERR_RSC_UPLOAD_TYPE_LIMIT, "file type limit");
		}
		
		for(MultipartFile file : files) {
			result = check(file, type);
			
			if(result.getCode() != ErrorCode.SUCCESS.longValue()) {
				return result;
			}
		}
		return result;
	}
	
	@Override
	public String genPortraitId(Long uid) {
		// uid + '-' + 随机数(32)
		return uid + "-" + RandomUtil.genCharacterString(32);
	}

	@Override
	public String genResourceId() {
		Resource resource = null;
		int retry = 0;
		String id = null;
		do {
			retry++;
			// 随机数(128)
			id = RandomUtil.genCharacterString(128);
			resource = resourceDao.select(id);
		} while(retry < SystemConstant.FILE_SVC_RETRY_TIME && null != resource);
		
		if(retry == 3 && null != resource) {
			return null;
		} else {
			return id;
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IFileService#genResourceId(java.util.List, int)
	 */
	@Override
	public void genResourceIds(List<String> ids, int size) {
		String id = null;
		for(int i = 0; i < size; i++) {
			id = genResourceId(ids);
			if(null == id) {
				return;
			} else {
				ids.add(id);
			}
		}
	}
	
	private String genResourceId(List<String> ids) {
		Resource resource = null;
		int retry = 0;
		String id = null;
		do {
			retry++;
			// 随机数(128)
			id = RandomUtil.genCharacterString(128);
			resource = resourceDao.select(id);
		} while(retry < SystemConstant.FILE_SVC_RETRY_TIME && (null != resource || ids.contains(id)));
		
		if(retry == 3 && (null != resource || ids.contains(id))) {
			return null;
		} else {
			return id;
		}
	}

	@Override
	public String saveToOss(String tempDir, Long uid, MultipartFile file, BucketType bucket, String key) {
		// temp dir
		logger.debug("check temp dir...");
		String destDir = tempDir + uid + File.separator;
		String destFile = destDir + uid;
		if(!new File(destDir).isDirectory()) {
			// create temp dir
			logger.debug("create temp dir...");
			new File(destDir).mkdirs();
		}
		
		// save file
		OutputStream fileStream = null;
		try {
			logger.debug("save file to local server...");
			fileStream = new FileOutputStream(new File(destFile));
			FileCopyUtils.copy(file.getInputStream(), fileStream);
			
			// upload file to oss
			logger.debug("upload file to oss...");
			String urlPath = ossService.post(bucket.getKey(), key, destFile);
			return urlPath;
		} catch (Exception e) {
			logger.error("upload portrait fail!", e);
			return null;
		} finally {
			if(null != fileStream) {
				try {
					fileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// cleanup temp file
			logger.debug("cleanup temp file...");
			File delFile = new File(destFile);
			if(delFile.exists()){
				delFile.delete();
			}
			
			File delFold = new File(destDir);
			if(delFold.isDirectory()) {
				delFold.delete();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.api.service.IFileService#saveToOss(java.lang.String, java.lang.Long, java.util.List, com.mg.api.common.constant.BucketType, java.lang.String)
	 */
	@Override
	public List<String> saveToOss(String tempDir, Long uid, List<MultipartFile> files, BucketType bucket, List<String> keys) {
		// temp dir
		logger.debug("check temp dir...");
		String destDir = tempDir + uid + File.separator;
		Map<String, String> destFiles = new HashMap<String, String>();

		if(!new File(destDir).isDirectory()) {
			// create temp dir
			logger.debug("create temp dir...");
			new File(destDir).mkdirs();
		}
		
		// save file
		OutputStream fileStream = null;
		try {
			logger.debug("save file to local server...");
			String destFile = null;
			for(int i = 0; i < files.size(); i++) {
				destFile = destDir + uid + i;
				destFiles.put(keys.get(i), destFile);
				fileStream = new FileOutputStream(new File(destFile));
				FileCopyUtils.copy(files.get(i).getInputStream(), fileStream);
				fileStream.close();
			}
			
			// upload file to oss
			logger.debug("upload file to oss...");
			List<String> urlPaths = ossService.post(bucket.getKey(), destFiles);
			return urlPaths;
		} catch (Exception e) {
			logger.error("upload portrait fail!", e);
			return null;
		} finally {
			if(null != fileStream) {
				try {
					fileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// cleanup temp file
			logger.debug("cleanup temp file...");
			for(String destFile : destFiles.values()) {
				File delFile = new File(destFile);
				if(delFile.exists()){
					delFile.delete();
				}
			}
			
			File delFold = new File(destDir);
			if(delFold.isDirectory()) {
				delFold.delete();
			}
		}
	}
	
	@Override
	public ResourceType getRscType(int type) {
		switch(type) {
		case SystemConstant.RESOURCE_TYPE_IMAGE:
			return ResourceType.IMAGE;
		case SystemConstant.RESOURCE_TYPE_AUDIO:
			return ResourceType.AUDIO;
		case SystemConstant.RESOURCE_TYPE_VIDEO:
			return ResourceType.VIDEO;
		default :
			return null;
		}
	}

	@Override
	public BucketType getBktType(int type) {
		switch(type) {
		case SystemConstant.RESOURCE_TYPE_IMAGE:
			return BucketType.IMAGE;
		case SystemConstant.RESOURCE_TYPE_AUDIO:
			return BucketType.AUDIO;
		case SystemConstant.RESOURCE_TYPE_VIDEO:
			return BucketType.VIDEO;
		default :
			return null;
		}
	}
}