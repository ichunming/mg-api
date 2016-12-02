package com.ichunming.mg.common.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.ichunming.mg.common.util.EmailUtil;
import com.ichunming.mg.common.util.helper.MailConfiguration;

/**
 * Unit test for simple App.
 */
public class EmailUtilTest {
	@Test
	public void test() throws InterruptedException {
		
		boolean result = true;
		
		// send simple email
		MailConfiguration config = new MailConfiguration();
		config.setHost("smtp.126.com"); // smtp.126.com
		config.setUsername("**@126.com"); // test@126.com
		config.setPassword("**"); // password
		config.setFrom("**@126.com"); // test@126.com
		config.setFromName("**"); // test
		config.setCharset("utf-8");
		
		String content = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><p>点击链接<a href='code=#{code}'>this is url</a>前往</p></body></html>";
		
		// send email with attachment
		List<Map<String, String>> attachs = new ArrayList<Map<String, String>>();
		Map<String, String> attach = new HashMap<String, String>();
		attach.put("name", "test.txt");
		attach.put("path", "D:\\temp\\test.txt");
		attachs.add(attach);
		
		try {
			result = EmailUtil.send(config, "主题", content, "ming.zhang@17mengshare.com");
			//result = MailUtil.sendHtml(config, "主题", content, "**@17mengshare.com");
			//result = MailUtil.send(config, "主题", content, "**@17mengshare.com", attachs);
			//result = MailUtil.sendHtml(config, "主题", content, "**@17mengshare.com", attachs);
			System.out.println("test finish!");
			//Thread.sleep(3000);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result);
	}
}
