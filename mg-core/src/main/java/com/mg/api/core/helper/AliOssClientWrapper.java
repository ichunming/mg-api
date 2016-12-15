/**
 * OSS Client Wrapper
 * create by ming 2016/11/18
 */
package com.mg.api.core.helper;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.ServiceException;
import com.mg.api.core.configuration.AliConfiguration;

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
	public String post(String key, InputStream is) throws ClientException, ServiceException {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.putObject(bucket, key, is);
		} catch(ClientException | ServiceException e) {
			logger.error("post inputstream to oss fail.", e);
			throw e;
		}
		finally {
			close(ossClient);
		}
		
		return bucketUrl + key;
	}

	/**
	 * 上传资源(文件)
	 * @param key
	 * @param filePath
	 */
	public String post(String key, String filePath) throws ClientException, ServiceException {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.putObject(bucket, key, new File(filePath));
		} catch(ClientException | ServiceException e) {
			logger.error("post file to oss fail.", e);
			throw e;
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
	public List<String> post(Map<String, String> fileMap) throws ClientException, ServiceException {
		OSSClient ossClient = null;
		List<String> urls = new ArrayList<String>();
		try{
			ossClient = getClient();
			for(String key : fileMap.keySet()) {
				ossClient.putObject(bucket, key, new File(fileMap.get(key)));
				urls.add(bucketUrl + key);
			}
		} catch(ClientException | ServiceException e) {
			logger.error("error occur when post multi file to oss.", e);
			throw e;
		} finally {
			close(ossClient);
		}
		
		return urls;
	}
	
	/**
	 * 删除资源
	 * @param key
	 */
	public void delete(String key) throws ClientException, ServiceException {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			ossClient.deleteObject(bucket, key);
		} catch(ClientException | ServiceException e) {
			logger.error("delete from oss fail.", e);
			throw e;
		} finally {
			close(ossClient);
		}
	}

	/**
	 * 批量删除资源
	 * @param key
	 */
	public void delete(List<String> keys) throws ClientException, ServiceException {
		OSSClient ossClient = null;
		try{
			ossClient = getClient();
			for(String key : keys) {
				ossClient.deleteObject(bucket, key);
			}
		} catch(ClientException | ServiceException e) {
			logger.error("delete from oss fail.", e);
			throw e;
		} finally {
			close(ossClient);
		}
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
