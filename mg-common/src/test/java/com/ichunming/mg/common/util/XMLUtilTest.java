package com.ichunming.mg.common.util;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.ichunming.mg.common.util.XMLUtil;
import com.ichunming.mg.common.util.entity.TestUser;

public class XMLUtilTest {

	@Test
	public void readTest() {
		String path = this.getClass().getClassLoader().getResource("info.xml").getPath();
		List<TestUser> users = XMLUtil.read(path, TestUser.class);
		for(TestUser user : users) {
			System.out.println(user.toString());
		}
		assertTrue(2 == users.size());
	}
	
	@Test
	public void writeTest() {
		String fold = this.getClass().getClassLoader().getResource("").getPath();
		List<TestUser> users = Arrays.asList(new TestUser("ming", "1234"), new TestUser("ning", "1234"));
		System.out.println(fold);
		XMLUtil.write(users, fold + "out_user.xml", TestUser.class);
	}
}
