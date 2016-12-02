/**
 * OSS Client Wrapper
 * create by ming 2016/11/18
 */
package com.ichunming.mg.core.helper;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

public class AliOssClientWrapper {
	
	private Logger logger = LoggerFactory.getLogger(AliOssClientWrapper.class);
	
	private AliConfiguration ossConfiguration;
	
	private String bucket;
	
	private String bucketUrl;
	
	public AliOssClientWrapper(AliConfiguration ossConfiguration, String bucket, String bucketUrl) {
		this.ossConfiguration = ossConfiguration;
		this.bucket = bucket;
		this.bucketUrl = bucketUrl;
	}
	
	/**
	 * 上传资源
	 * @param key
	 * @param is
	 * @return
	 */
	public String post(String key, InputStream is) {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.putObject(bucket, key, is);
		} catch(Exception e) {
			logger.error("post inputstream to oss fail.", e);
			return null;
		} finally {
			close(ossClient);
		}
		
		return bucketUrl + key;
	}

	/**
	 * 上传资源(文件)
	 * @param key
	 * @param filePath
	 */
	public String post(String key, String filePath) {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.putObject(bucket, key, new File(filePath));
		} catch(Exception e) {
			logger.error("post file to oss fail.", e);
			return null;
		} finally {
			close(ossClient);
		}
		
		return bucketUrl + key;
	}
	
	/**
	 * 批量上传资源(文件)
	 * @param key
	 * @param filePath
	 */
	public List<String> post(Map<String, String> fileMap) {
		OSSClient ossClient = null;
		List<String> urls = new ArrayList<String>();
		try{
			ossClient = getClient();
			for(String key : fileMap.keySet()) {
				ossClient.putObject(bucket, key, new File(fileMap.get(key)));
				urls.add(bucketUrl + key);
			}
		} catch(Exception e) {
			logger.error("error occur when post file to oss.", e);
		} finally {
			close(ossClient);
		}
		
		return urls;
	}
	
	/**
	 * 删除资源
	 * @param key
	 */
	public boolean delete(String key) {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.deleteObject(bucket, key);
		} catch(Exception e) {
			logger.error("delete from oss fail.", e);
			return false;
		} finally {
			close(ossClient);
		}
		
		return true;
	}

	/**
	 * 批量删除资源
	 * @param key
	 */
	public boolean delete(List<String> keys) {
		OSSClient ossClient = null;
		boolean result = true;
		try{
			ossClient = getClient();
			for(String key : keys) {
				ossClient.deleteObject(bucket, key);
			}
		} catch(Exception e) {
			logger.error("delete from oss fail.", e);
			result = false;
		} finally {
			close(ossClient);
		}
		
		return result;
	}
	
	/**
	 * 取得客户端
	 * @return
	 */
	private OSSClient getClient() {
		return new OSSClient(ossConfiguration.getEndpoint(), ossConfiguration.getAccessKeyId(), ossConfiguration.getAccessKeySecret()); 
	}
	
	/**
	 * 取得客户端
	 * @return
	 */
	private void close(OSSClient ossClient) {
		if(null != ossClient) {
			ossClient.shutdown();
		}
	}
}
