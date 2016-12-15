package com.mg.api.core.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mg.api.core.configuration.EmailConfiguration;
import com.mg.api.core.service.EmailService;
import com.mg.api.entity.EmailTplMsgEntity;

public class EmailServiceTest extends BaseTest {
	@InjectMocks
	private EmailService target;
	@Mock
	private EmailConfiguration config;
	
	private EmailTplMsgEntity msg;
	
	private List<Map<String, String>> attachs = new ArrayList<Map<String, String>>();
	
	@Test
	public void sendTest() {
		// prepare data
		prepareData();
		
		// mock data
		mockData();
		
		// test method
		boolean result = target.send(msg);
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendWithAttTest() {
		// prepare data
		prepareData();
		
		// mock data
		mockData();
		
		// test method
		boolean result = target.send(msg, attachs);
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendHtmlTest() {
		// prepare data
		prepareData();
		
		// mock data
		mockData();
		
		// test method
		boolean result = target.sendHtml(msg);
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendHtmlWithAttTest() {
		// prepare data
		prepareData();
		
		// mock data
		mockData();
		
		// test method
		boolean result = target.sendHtml(msg, attachs);
		
		// verify result
		assertTrue(result);
	}
	
	@Ignore
	private void prepareData() {
		this.msg = new EmailTplMsgEntity();
		this.msg.setSubject("Validate");
		this.msg.setContent("<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><p>验证码：<b>${code!''}</b>，有效时间3分钟</p></body></html>");
		this.msg.setTo("");
		
		Map<String, String> attach = new HashMap<String, String>();
		attach.put("name", "test.txt");
		attach.put("path", "D:\\temp\\test.txt");
		this.attachs.add(attach);
	}
	
	@Ignore
	private void mockData() {
		when(config.getHost()).thenReturn("");
		when(config.getUsername()).thenReturn("");
		when(config.getPassword()).thenReturn("");
		when(config.getFrom()).thenReturn("");
		when(config.getFromName()).thenReturn("");
		when(config.getCharset()).thenReturn("");
	}
}
