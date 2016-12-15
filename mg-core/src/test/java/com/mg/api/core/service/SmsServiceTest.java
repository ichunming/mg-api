package com.mg.api.core.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.mg.api.core.configuration.AliConfiguration;
import com.mg.api.core.service.SmsService;

public class SmsServiceTest {
	private SmsService target;
	
	private AliConfiguration config;
	
	private List<String> receiver;
	
	private Map<String, String> param;
	
	@Test
	public void sendValidationTest() {
		// prepare data
		prepareData();
		
		// test method
		boolean result = target.sendValidation(this.receiver, this.param);
		
		// verify result
		assertTrue(result);
	}
	
	@Ignore
	private void prepareData() {
		this.config = new AliConfiguration();
		this.config.setEndpoint("");
		this.config.setAccessKeyId("");
		this.config.setAccessKeySecret("");
		this.config.setSmsSignName("");
		this.config.setSmsTplValidateCode("");
		this.config.setSmsTplNotifyCode("");
		
		this.target = new SmsService();
		target.setConfig(config);
		
		this.receiver = Arrays.asList("");
		this.param = new HashMap<String, String>();
		this.param.put("code", "7236");
	}
}
