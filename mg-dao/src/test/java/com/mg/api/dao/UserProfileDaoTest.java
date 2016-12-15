package com.mg.api.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.mg.api.dao.UserProfileDao;
import com.mg.api.model.UserProfile;

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
	
	@Test
	public void updateTest() throws Exception {
		// create component
		UserProfileDao profileDao = sqlSession.getMapper(UserProfileDao.class);

		// prepare data
		UserProfile profile = new UserProfile();
		profile.setUid(1L);
		profile.setNickname("nickname**");
		profile.setPortrait("portrait**");
		profile.setRealName("realName**");
		profileDao.insert(profile);
		String newNickname = "new nickname";
		profile.setNickname(newNickname);
		
		// test method
		profileDao.update(profile);
		
		// verify result
		UserProfile verProfile = selectOne("select * from user_profile where uid = 1", UserProfile.class);
		assertEquals(newNickname, verProfile.getNickname());
	}
}
