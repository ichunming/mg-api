package com.mg.api.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mg.api.dao.UserDao;
import com.mg.api.entity.UserView;
import com.mg.api.model.User;

public class UseDaoTest extends BaseTest {
	@Before
	public void setUp() throws Exception {
		cleanTbl("user");
	}
		
	@Test
	public void getByEmailTest() throws Exception {
		// create component
		UserDao userDao = sqlSession.getMapper(UserDao.class);

		// prepare data
		addUser();
		
		// test method
		User user = userDao.getByEmail("test@test.com");
		
		// verify result
		assertNotNull(user);
		assertEquals("test@test.com", user.getEmail());
	}
	
	@Test
	public void getByMobileTest() throws Exception {
		// create component
		UserDao userDao = sqlSession.getMapper(UserDao.class);

		// prepare data
		addUser();
		
		// test method
		User user = userDao.getByMobile("13761104110");
		
		// verify result
		assertNotNull(user);
		assertEquals("13761104110", user.getMobile());
	}
	
	@Test
	public void getByUsername_Email_Test() throws Exception {
		// create component
		UserDao userDao = sqlSession.getMapper(UserDao.class);

		// prepare data
		addUser();
		
		// test method
		UserView userView = userDao.getViewByUsername("test@test.com");
		
		// verify result
		assertNotNull(userView);
		assertEquals("test@test.com", userView.getEmail());
	}
	
	@Test
	public void getByUsername_Mobile_Test() throws Exception {
		// create component
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		
		// prepare data
		addUser();
		
		// test method
		UserView userView = userDao.getViewByUsername("13761104110");
		
		// verify result
		assertNotNull(userView);
		assertEquals("13761104110", userView.getMobile());
	}
	
	@Test
	public void insertTest() throws Exception {
		// create component
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		
		// prepare data
		User user = new User();
		user.setEmail("test***@test.com");
		user.setMobile("137****4110");
		user.setPassword("pwd***");
		user.setSalt("salt***");
		user.setStatus(1);
		
		// test method
		userDao.insert(user);
		
		// verify result
		assertNotNull(user.getId());
	}
	
	@Ignore
	private void addUser() {
		insert("insert into user values(1, 'pwd', 'salt', 'test@test.com', '13761104110', 1, now(), now())");
	}
}
