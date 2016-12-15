/**
 * UserController
 * ming 2016/12/12
 */
package com.mg.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.common.constant.BucketType;
import com.mg.api.common.constant.ErrorCode;
import com.mg.api.common.constant.SystemSettings;
import com.mg.api.common.constant.UploadFileType;
import com.mg.api.common.util.StringUtil;
import com.mg.api.core.service.FileService;
import com.mg.api.core.service.OssService;
import com.mg.api.vo.BaseResult;

@Controller
@ResponseBody
@RequestMapping("/v1/file")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private OssService ossService;
	
	@RequestMapping(value = "upload/image", method = RequestMethod.POST)
	public BaseResult imageUpload(MultipartFile file, HttpServletRequest request) {
		// file empty check
		if(file.isEmpty()) {
			logger.debug("empty file!");
			return new BaseResult(ErrorCode.ERR_SYS_REQUEST_PARAM_INVALID, "empty file");
		}
		
		// file type check
		if(!UploadFileType.IMAGE.getTypes().contains(file.getContentType())) {
			logger.info("file type incorrect!");
			return new BaseResult(ErrorCode.ERR_USER_UPLOAD_TYPE_LIMIT, "incorrect file type");
		}
		
		// file size check
		if(file.getSize() > UploadFileType.IMAGE.getMaxSize()) {
			logger.info("file is oversize!");
			return new BaseResult(ErrorCode.ERR_USER_UPLOAD_SIZE_LIMIT, "file oversize");
		}
		
		// temp dir
		logger.debug("get temp dir...");
		String destDir = request.getSession().getServletContext().getRealPath("/") + SystemSettings.TEMP_DIR;
		if(!new File(destDir).isDirectory()) {
			// create temp dir
			logger.debug("create temp dir...");
			new File(destDir).mkdirs();
		}
		
		// create file name
		logger.debug("create image file name...");
		String name = fileService.getImageName();
		if(StringUtil.isEmpty(name)) {
			logger.debug("create image file name file!");
			return new BaseResult(ErrorCode.ERR_SVR_FILE_ERROR, "create file name fail");
		}
		// save file
		OutputStream fileStream = null;
		logger.debug("save file...");
		try {
			fileStream = new FileOutputStream(new File(destDir + File.separator + name));
			FileCopyUtils.copy(file.getInputStream(), fileStream);
			
			// upload to oss
			logger.debug("upload file to oss...");
			ossService.post(BucketType.IMAGE.getKey(), name, destDir + File.separator + name);
		} catch (Exception e) {
			logger.error("upload image fail!");
			return new BaseResult(ErrorCode.ERR_SYS_INTERNAL_ERROR, "upload image fail!");
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
			File delFile = new File(destDir + File.separator + name);
			if(delFile.exists()){
				delFile.delete();
			}
		}
		
		return new BaseResult(ErrorCode.SUCCESS);
	}
}
