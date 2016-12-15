package com.mg.api.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.mg.api.common.util.JsonUtil;
import com.mg.api.common.util.entity.TestBaseResult;
import com.mg.api.common.util.entity.TestUser;

public class JsonUtilTest {
	
	@Test
	public void toJson() {
		TestUser user = new TestUser("User1", "p1");
		String json = JsonUtil.toJson(user);
		System.out.println(json);
		
		TestUser obj = JsonUtil.fromJson(json, TestUser.class);
		assertEquals(obj.getName(), user.getName());
	}
	
	@Test
	public void fromJsonTest() {
		List<TestUser> users = Arrays.asList(new TestUser("User1", "p1"), new TestUser("User2", "p2"));
		TestBaseResult ret = new TestBaseResult();
		ret.setCode(0L);
		ret.setData(users);
		String json = JsonUtil.toJson(ret);
		System.out.println(json);
		
		List<TestUser> objs = JsonUtil.fromJson(json, "data", TestUser.class);
		assertEquals(objs.size(), users.size());
	}
	
	@Test
	public void fromJsonExTest() {
		TestUser obj = JsonUtil.fromJson("not json data", TestUser.class);
		List<TestUser> objs = JsonUtil.fromJson("{\"code\":0,\"dataEx\":[{\"name\":\"User1\",\"password\":\"p1\"},{\"name\":\"User2\",\"password\":\"p2\"}]}",
							"data", TestUser.class);
		assertNull(obj);
		assertNull(objs);
	}
}