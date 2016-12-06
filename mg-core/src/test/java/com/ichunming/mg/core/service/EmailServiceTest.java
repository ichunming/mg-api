package com.ichunming.mg.core.service;

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

import com.ichunming.mg.common.util.helper.MailConfiguration;

public class EmailServiceTest extends BaseTest {
	@InjectMocks
	private EmailService target;
	@Mock
	private MailConfiguration config;
	
	private String subject;
	
	private String content;
	
	private String toEmail;
	
	private List<Map<String, String>> attachs = new ArrayList<Map<String, String>>();
	
	@Test
	public void sendTest() {
		// prepare data
		prepareData();
		
		// mock data
		mockData();
		
		// test method
		boolean result = target.send(this.subject, this.content, this.toEmail);
		
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
		boolean result = target.send(this.subject, this.content, this.toEmail, attachs);
		
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
		boolean result = target.sendHtml(this.subject, this.content, this.toEmail);
		
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
		boolean result = target.sendHtml(this.subject, this.content, this.toEmail, attachs);
		
		// verify result
		assertTrue(result);
	}
	
	@Ignore
	private void prepareData() {
		this.subject = "Validate";
		this.content = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><p>点击链接<a href='code=#{code}'>this is url</a>前往</p></body></html>";
		this.toEmail = "";
		
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
