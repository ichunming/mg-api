package com.ichunming.mg.core.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.ichunming.mg.common.constant.BucketType;
import com.ichunming.mg.core.configuration.AliConfiguration;

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
