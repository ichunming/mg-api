package com.ichunming.mg.core.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.ichunming.mg.common.constant.BucketType;
import com.ichunming.mg.core.helper.AliConfiguration;

public class OssServiceTest {
	private OssService target;
	
	private AliConfiguration config;
	
	@Test
	public void postTest() {
		// prepare data
		prepareData();
		
		// test method
		String result = target.post(BucketType.PIC.getKey(), "test", "D:\\Share\\01.jpg");
		
		// verify result
		assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		// prepare data
		prepareData();
		
		// test method
		boolean result = target.delete(BucketType.PIC.getKey(), "test");
		
		// verify result
		assertTrue(result);
	}
	
	@Ignore
	private void prepareData() {
		this.config = new AliConfiguration();
		this.config.setEndpoint("");
		this.config.setAccessKeyId("");
		this.config.setAccessKeySecret("");
		this.config.setOssBktPicName("");
		this.config.setOssBktPicUrl("");
		
		this.target = new OssService();
		target.setConfig(config);
	}
}
