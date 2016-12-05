/**
 * OSS Client Wrapper
 * create by ming 2016/11/18
 */
package com.ichunming.mg.core.helper;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

public class OssClientWrapper {
	
	private Logger logger = LoggerFactory.getLogger(OssClientWrapper.class);
	
	private OssConfiguration ossConfiguration;
	
	private String bucket;
	
	private String bucketUrl;
	
	public OssClientWrapper(OssConfiguration ossConfiguration, String bucket, String bucketUrl) {
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
			logger.debug("post inputstream to oss fail.", e);
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
			logger.debug("post file to oss fail.", e);
			return null;
		} finally {
			close(ossClient);
		}
		
		return bucketUrl + key;
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
			logger.debug("delete from oss fail.", e);
			return false;
		} finally {
			close(ossClient);
		}
		
		return true;
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
