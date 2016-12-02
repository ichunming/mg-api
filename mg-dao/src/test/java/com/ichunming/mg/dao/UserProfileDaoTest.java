package com.ichunming.mg.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.ichunming.mg.model.UserProfile;

public class UserProfileDaoTest extends BaseTest {
	@Before
	public void setUp() throws Exception {
		cleanTbl("user_profile");
	}
		
	@Test
	public void insertTest() throws Exception {
		// create component
		UserProfileDao profileDao = sqlSession.getMapper(UserProfileDao.class);

		// prepare data
		UserProfile profile = new UserProfile();
		profile.setUid(1L);
		profile.setNickname("nickname**");
		profile.setPortrait("portrait**");
		profile.setRealName("realName**");
		
		// test method
		profileDao.insert(profile);
		
		// verify result
		UserProfile verProfile = selectOne("select * from user_profile where uid = 1", UserProfile.class);
		assertNotNull(verProfile);
	}
}
