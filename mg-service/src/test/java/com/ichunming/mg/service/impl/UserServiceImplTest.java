package com.ichunming.mg.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.common.constant.UserStatus;
import com.ichunming.mg.dao.UserDao;
import com.ichunming.mg.dao.UserProfileDao;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.model.UserView;
import com.ichunming.mg.service.BaseTest;

public class UserServiceImplTest extends BaseTest{
	@InjectMocks
	private UserServiceImpl target;
	@Mock
	private UserDao userDao;
	@Mock
	private UserProfileDao profileDao;
	
	private static UserView user;
	
	private static final String MOBILE = "13761104110";
	
	private static final String EMAIL = "test@test.com";
	
	private static final String ORG_PWD = "test";
	
	private static final String EN_PWD = "3102356d9203545ebf4267e32dced7d7";
	
	private static final String SALT = "93f6ec86f93d3f6318f831b4caf1f1dd";
	
	@Test
	public void registerByEmailTest() {
		// prepare data

		// mock data
		
		// test method
		BaseResult result = target.registerByEmail(EMAIL, ORG_PWD, "");
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.SUCCESS.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void registerByMobileTest() {
		// prepare data
		
		// mock data
		
		// test method
		BaseResult result = target.registerByMobile(MOBILE, ORG_PWD, "");
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.SUCCESS.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void loginTest() {
		// prepare data
		createUser(UserStatus.Active.getCode());
		
		// mock data
		when(userDao.getViewByUsername(Mockito.anyString())).thenReturn(user);
		
		// test method
		BaseResult result = target.login(EMAIL, ORG_PWD);
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.SUCCESS.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void login_Invalid_Test() {
		// prepare data
		createUser(UserStatus.Invalid.getCode());
		
		// mock data
		when(userDao.getViewByUsername(Mockito.anyString())).thenReturn(user);
		
		// test method
		BaseResult result = target.login(EMAIL, ORG_PWD);
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.ERR_USER_UNAUTHEN.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void login_Locked_Test() {
		// prepare data
		createUser(UserStatus.Locked.getCode());
		
		// mock data
		when(userDao.getViewByUsername(Mockito.anyString())).thenReturn(user);
		
		// test method
		BaseResult result = target.login(EMAIL, ORG_PWD);
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.ERR_USER_LOCK.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void login_Deleted_Test() {
		// prepare data
		createUser(UserStatus.Deleted.getCode());
		
		// mock data
		when(userDao.getViewByUsername(Mockito.anyString())).thenReturn(user);
		
		// test method
		BaseResult result = target.login(EMAIL, ORG_PWD);
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.ERR_USER_DELETE.longValue(), result.getCode().longValue());
	}
	
	@Test
	public void login_PwdInvalid_Test() {
		// prepare data
		createUser(UserStatus.Active.getCode());
		
		// mock data
		when(userDao.getViewByUsername(Mockito.anyString())).thenReturn(user);
		
		// test method
		BaseResult result = target.login(EMAIL, "error pwd");
		
		// verify result
		assertNotNull(result);
		assertEquals(ErrorCode.ERR_USER_PASSWD_INVALID.longValue(), result.getCode().longValue());
	}

	@Test
	public void isEmailExist_True_Test() {
		// prepare data
		
		// mock data
		when(userDao.getCntByEmail(Mockito.anyString())).thenReturn(1);
		
		// test method
		boolean result = target.isEmailExist("test@test.com");
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void isEmailExist_False_Test() {
		// prepare data
		
		// mock data
		when(userDao.getCntByEmail(Mockito.anyString())).thenReturn(0);
		
		// test method
		boolean result = target.isEmailExist("test@test.com");
		
		// verify result
		assertFalse(result);
	}
	
	@Test
	public void isMobileExist_True_Test() {
		// prepare data
		
		// mock data
		when(userDao.getCntByMobile(Mockito.anyString())).thenReturn(1);
		
		// test method
		boolean result = target.isMobileExist("13761104110");
		
		// verify result
		assertTrue(result);
	}
	
	@Test
	public void isMobileExist_False_Test() {
		// prepare data
		
		// mock data
		when(userDao.getCntByMobile(Mockito.anyString())).thenReturn(0);
		
		// test method
		boolean result = target.isMobileExist("13761104110");
		
		// verify result
		assertFalse(result);
	}
	
	@Ignore
	private void createUser(int status) {
		if(null == user) {
			user = new UserView();
		}
		user.setEmail(EMAIL);
		user.setMobile(MOBILE);
		user.setSalt(SALT);
		user.setPassword(EN_PWD);
		user.setStatus(status);
	}
}
