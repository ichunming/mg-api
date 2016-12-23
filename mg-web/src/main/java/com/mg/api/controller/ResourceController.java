/**
 * Resource Controller
 * ming 2016/12/12
 */
package com.mg.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mg.api.vo.BaseResult;

@Controller
@ResponseBody
@RequestMapping("/v1/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public BaseResult upload(MultipartFile file, int type, HttpServletRequest request) {
		return null;
	}
	
	@RequestMapping(value = "upload/batch", method = RequestMethod.POST)
	public BaseResult batchUpload(List<MultipartFile> files, int type, HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public BaseResult delete(List<String> ids, HttpServletRequest request) {
		return null;
	}
	
	@RequestMapping(value = "get/list", method = RequestMethod.POST)
	public BaseResult getList(int type, @RequestParam(required = false, defaultValue = "1") int page, HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "statistics", method = RequestMethod.POST)
	public BaseResult statistics(HttpServletRequest request) {
		return null;
	}
}
