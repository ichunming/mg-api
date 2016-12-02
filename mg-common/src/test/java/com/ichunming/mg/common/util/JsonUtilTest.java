package com.ichunming.mg.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.ichunming.mg.common.util.JsonUtil;
import com.ichunming.mg.common.util.entity.TestBaseResult;
import com.ichunming.mg.common.util.entity.UserTest;

public class JsonUtilTest {
	
	@Test
	public void toJson() {
		UserTest user = new UserTest("User1", "p1");
		String json = JsonUtil.toJson(user);
		System.out.println(json);
		
		UserTest obj = JsonUtil.fromJson(json, UserTest.class);
		assertEquals(obj.getName(), user.getName());
	}
	
	@Test
	public void fromJsonTest() {
		List<UserTest> users = Arrays.asList(new UserTest("User1", "p1"), new UserTest("User2", "p2"));
		TestBaseResult ret = new TestBaseResult();
		ret.setCode(0L);
		ret.setData(users);
		String json = JsonUtil.toJson(ret);
		System.out.println(json);
		
		List<UserTest> objs = JsonUtil.fromJson(json, "data", UserTest.class);
		assertEquals(objs.size(), users.size());
	}
	
	@Test
	public void fromJsonExTest() {
		UserTest obj = JsonUtil.fromJson("not json data", UserTest.class);
		List<UserTest> objs = JsonUtil.fromJson("{\"code\":0,\"dataEx\":[{\"name\":\"User1\",\"password\":\"p1\"},{\"name\":\"User2\",\"password\":\"p2\"}]}",
							"data", UserTest.class);
		assertNull(obj);
		assertNull(objs);
	}
}