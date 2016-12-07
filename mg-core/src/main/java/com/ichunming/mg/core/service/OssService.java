/**
 * User Service
 * 2016/10/09 ming
 * v0.1
 */
package com.ichunming.mg.core.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichunming.mg.common.constant.BucketType;
import com.ichunming.mg.core.exception.OssServiceException;
import com.ichunming.mg.core.helper.AliConfiguration;
import com.ichunming.mg.core.helper.AliOssClientWrapper;

@Service
public class OssService {
	private Logger logger = LoggerFactory.getLogger(OssService.class);
	
	private Map<String, AliOssClientWrapper> ossClientMap;
	
	@Autowired
	private AliConfiguration config;
	
	/**
	 * init
	 * @return
	 */
	private void initOssClientMap(){
		logger.debug("init client map...");
		ossClientMap = new HashMap<String, AliOssClientWrapper>();
		
		//add module
		ossClientMap.put(BucketType.FILE.getKey(), new AliOssClientWrapper(config, config.getOssBktFileName(), config.getOssBktFileUrl()));
		ossClientMap.put(BucketType.PIC.getKey(), new AliOssClientWrapper(config, config.getOssBktPicName(), config.getOssBktPicUrl()));
		ossClientMap.put(BucketType.AUDIO.getKey(), new AliOssClientWrapper(config, config.getOssBktAudioName(), config.getOssBktAudioUrl()));
		ossClientMap.put(BucketType.VIDEO.getKey(), new AliOssClientWrapper(config, config.getOssBktVideoName(), config.getOssBktVideoUrl()));
		ossClientMap.put(BucketType.OTHER.getKey(), new AliOssClientWrapper(config, config.getOssBktOtherName(), config.getOssBktOtherUrl()));
	}
	
	/**
	 * post
	 * @param bucketKey
	 * @param key
	 * @param is
	 * @return
	 */
	public String post(String bucketKey, String key, InputStream is) {
		// get client
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);

		String url = null;
		
		try {
			url = ossClientWrapper.post(key, is);
		} catch(Exception e) {
			throw new OssServiceException(e);
		}
		return url;
	}
	
	/**
	 * post
	 * @param bucketKey
	 * @param key
	 * @param filePath
	 * @return
	 */
	public String post(String bucketKey, String key, String filePath) {
		// get client
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		
		String url = null;
		
		try {
			url = ossClientWrapper.post(key, filePath);
		} catch(Exception e) {
			throw new OssServiceException(e);
		}
		return url;
	}
	
	/**
	 * post multifile
	 * @param bucketKey
	 * @param key
	 * @param filePath
	 * @return
	 */
	public List<String> post(String bucketKey, Map<String, String> fileMap) {
		// get client
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		
		List<String> urls = null;
		
		try {
			urls = ossClientWrapper.post(fileMap);
		} catch(Exception e) {
			throw new OssServiceException(e);
		}
		return urls;
	}
	
	/**
	 * delete
	 * @param bucketKey
	 * @param key
	 */
	public void delete(String bucketKey, String key) {
		// get client
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		
		try {
			ossClientWrapper.delete(key);
		} catch(Exception e) {
			throw new OssServiceException(e);
		}
	}
	
	/**
	 * delete multifile
	 * @param bucketKey
	 * @param files
	 */
	public void delete(String bucketKey, List<String> keys) {
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		
		try {
			ossClientWrapper.delete(keys);
		} catch(Exception e) {
			throw new OssServiceException(e);
		}
	}
	
	/**
	 * get client
	 * @param bucketKey
	 * @return
	 */
	private AliOssClientWrapper getClient(String bucketKey) {
		// check ossClientMap
		if(null == ossClientMap || ossClientMap.isEmpty()) {
			initOssClientMap();
		}
		// check ossClientWrapper
		return ossClientMap.get(bucketKey.toUpperCase());
	}
	
	public void setConfig(AliConfiguration config) {
		this.config = config;
	}
}
