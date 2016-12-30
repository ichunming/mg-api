package com.mg.api.core.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.mg.api.common.constant.BucketType;
import com.mg.api.core.configuration.AliConfiguration;

public class OssServiceTest {
	private OssService target;
	
	private AliConfiguration config;
	
	@Test
	public void postTest() {
		// prepare data
		prepareData();
		
		// test method
		String result = target.post(BucketType.IMAGE.getKey(), "test", "D:\\Share\\01.jpg");
		
		// verify result
		assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		// prepare data
		prepareData();
		
		// test method
		target.delete(BucketType.IMAGE.getKey(), "test");
		
		// verify result
	}

	@Test
	public void convertUrls2KeysTest() {
		// prepare data
		prepareData();
		List<String> urls = Arrays.asList("http://image.oss-cn-shanghai.aliyuncs.com/0001", "http://image.oss-cn-shanghai.aliyuncs.com/0002");
		
		// test method
		List<String> keys = target.convertUrls2Keys(urls);
		
		// verify result
		assertTrue(keys.contains("0001"));
		assertTrue(keys.contains("0002"));
	}
	
	@Ignore
	private void prepareData() {
		this.config = new AliConfiguration();
		this.config.setEndpoint("");
		this.config.setAccessKeyId("");
		this.config.setAccessKeySecret("");
		this.config.setOssBktImageName("");
		this.config.setOssBktImageUrl("");
		
		this.target = new OssService();
		target.setConfig(config);
	}
}
