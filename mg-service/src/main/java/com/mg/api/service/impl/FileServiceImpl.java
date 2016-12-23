/**
 * File Service Impl
 * create by ming 2016/12/21
 */
package com.mg.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.ResourceType;
import com.mg.api.common.util.RandomUtil;
import com.mg.api.dao.ResourceDao;
import com.mg.api.service.IFileService;
import com.mg.api.vo.BaseResult;

@Service
public class FileServiceImpl implements IFileService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public BaseResult check(MultipartFile file, ResourceType type) {
		// file empty check
		if(file.isEmpty()) {
			logger.debug("empty file!");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "empty file");
		}
		
		// file type check
		if(!type.getTypes().contains(file.getContentType())) {
			logger.info("file type incorrect!");
			return new BaseResult(ErrorCode.ERR_USER_UPLOAD_TYPE_LIMIT, "incorrect file type");
		}
		
		// file size check
		if(file.getSize() > type.getMaxSize()) {
			logger.info("file is oversize!");
			return new BaseResult(ErrorCode.ERR_USER_UPLOAD_SIZE_LIMIT, "file oversize");
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}

	@Override
	public String genPortraitId(Long uid) {
		// uid + 随机数(32)
		return uid + RandomUtil.genCharacterString(32);
	}

	@Override
	public String genResourceId() {
		// TODO Auto-generated method stub
		return null;
	}
}