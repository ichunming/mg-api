/**
 * email service
 * ming 2016/12/12
 */
package com.mg.api.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.api.common.util.RandomUtil;
import com.mg.api.dao.PhotoDao;
import com.mg.api.model.Photo;

@Service
public class FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);
	
	private static final int RETRY_TIMES = 3;
	
	@Autowired
	private PhotoDao photoDao;
	
	public String getImageName() {
		String name = null;
		Photo photo = null;
		
		for(int time = 0; time < RETRY_TIMES; time++) {
			// 随机生成
			name = RandomUtil.genCharacterString(32);
			
			// 重复性check
			photo = photoDao.getByName(name);
			
			if(null == photo) {
				return name;
			}
		}
		
		return null;
	}
}
