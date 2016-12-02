package com.ichunming.mg.common.util;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.ichunming.mg.common.util.XMLUtil;
import com.ichunming.mg.common.util.entity.UserTest;

public class XMLUtilTest {

	@Test
	public void readTest() {
		String path = this.getClass().getClassLoader().getResource("info.xml").getPath();
		List<UserTest> users = XMLUtil.read(path, UserTest.class);
		for(UserTest user : users) {
			System.out.println(user.toString());
		}
		assertTrue(2 == users.size());
	}
	
	@Test
	public void writeTest() {
		String fold = this.getClass().getClassLoader().getResource("").getPath();
		List<UserTest> users = Arrays.asList(new UserTest("ming", "1234"), new UserTest("ning", "1234"));
		System.out.println(fold);
		XMLUtil.write(users, fold + "out_user.xml", UserTest.class);
	}
}
