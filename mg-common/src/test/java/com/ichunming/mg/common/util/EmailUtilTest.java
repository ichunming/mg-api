package com.ichunming.mg.common.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ichunming.mg.common.util.helper.MailConfiguration;

/**
 * Unit test for simple App.
 */
public class EmailUtilTest {
	private static MailConfiguration config;
	
	private static String subject;
	
	private static String content;
	
	private static String toEmail;
	
	private static List<Map<String, String>> attachs;
	
	// 所有方法执行一次
	@BeforeClass
	public static void beforeClass() {
		subject = "Validate";
		content = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><p>点击链接<a href='code=#{code}'>this is url</a>前往</p></body></html>";
		toEmail = "";
		
		attachs = new ArrayList<Map<String, String>>();
		Map<String, String> attach = new HashMap<String, String>();
		attach.put("name", "test.txt");
		attach.put("path", "D:\\temp\\test.txt");
		attachs.add(attach);
		
		config = new MailConfiguration();
		config.setHost("");
		config.setUsername("");
		config.setPassword("");
		config.setFrom("");
		config.setFromName("");
		config.setCharset("");
	}
	
	@Test
	public void sendTest() {
		// test method
		boolean result = false;
		try {
			result = EmailUtil.send(config, subject, content, toEmail);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendWithAttTest() {
		// test method
		boolean result = false;
		try {
			result = EmailUtil.send(config, subject, content, toEmail, attachs);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendHtmlTest() {
		// test method
		boolean result = false;
		try {
			result = EmailUtil.sendHtml(config, subject, content, toEmail);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void sendHtmlWithAttTest() {
		// test method
		boolean result = false;
		try {
			result = EmailUtil.sendHtml(config, subject, content, toEmail, attachs);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		// verify result
		assertTrue(result);
	}
}
