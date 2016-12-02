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
import com.ichunming.mg.core.helper.AliOssClientWrapper;
import com.ichunming.mg.core.helper.AliConfiguration;

@Service
public class OssService {
	private Logger logger = LoggerFactory.getLogger(OssService.class);
	
	private Map<String, AliOssClientWrapper> ossClientMap;
	
	@Autowired
	private AliConfiguration ossConfiguration;
	
	/**
	 * init
	 * @return
	 */
	private void initOssClientMap(){
		logger.debug("init client map...");
		ossClientMap = new HashMap<String, AliOssClientWrapper>();
		
		//add module
		ossClientMap.put(BucketType.FILE.getKey(), new AliOssClientWrapper(ossConfiguration, ossConfiguration.getOssBktFileName(), ossConfiguration.getOssBktFileUrl()));
		ossClientMap.put(BucketType.PIC.getKey(), new AliOssClientWrapper(ossConfiguration, ossConfiguration.getOssBktPicName(), ossConfiguration.getOssBktPicUrl()));
		ossClientMap.put(BucketType.AUDIO.getKey(), new AliOssClientWrapper(ossConfiguration, ossConfiguration.getOssBktAudioName(), ossConfiguration.getOssBktAudioUrl()));
		ossClientMap.put(BucketType.VIDEO.getKey(), new AliOssClientWrapper(ossConfiguration, ossConfiguration.getOssBktVideoName(), ossConfiguration.getOssBktVideoUrl()));
		ossClientMap.put(BucketType.OTHER.getKey(), new AliOssClientWrapper(ossConfiguration, ossConfiguration.getOssBktOtherName(), ossConfiguration.getOssBktOtherUrl()));
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

		return ossClientWrapper.post(key, is);
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
		
		return ossClientWrapper.post(key, filePath);
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
		
		return ossClientWrapper.post(fileMap);
	}
	
	/**
	 * delete
	 * @param bucketKey
	 * @param key
	 */
	public boolean delete(String bucketKey, String key) {
		// get client
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		return ossClientWrapper.delete(key);
	}
	
	/**
	 * delete multifile
	 * @param bucketKey
	 * @param files
	 */
	public boolean delete(String bucketKey, List<String> keys) {
		AliOssClientWrapper ossClientWrapper = getClient(bucketKey);
		return ossClientWrapper.delete(keys);
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
	
}
