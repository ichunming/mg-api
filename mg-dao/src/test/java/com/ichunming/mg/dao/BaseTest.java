package com.ichunming.mg.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.github.abel533.sql.SqlMapper;

public class BaseTest {
	protected static SqlSession sqlSession;
	
	protected static SqlMapper sqlMapper;

	// 所有方法执行一次
	@BeforeClass
	public static void beforeClass() throws Exception {
		System.out.println("create sqlSession...");
		// create a SqlSessionFactory
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();

		// create session--auto commit
		sqlSession = sqlSessionFactory.openSession(true);
		
		// create sqlMapper--provide test
		sqlMapper = new SqlMapper(sqlSession);
	};

	// 所有方法执行一次
	@AfterClass
	public static void afterClass() {
		System.out.println("release sqlSession");
		if(null != sqlSession) {
			sqlSession.close();	
		}
	};

	// 每个测试方法执行之前都要执行一次。
	@Before
	public void setUp() throws Exception {
	}

	// 每个测试方法执行之后要执行一次。
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void run() {
	}
	
	@Ignore
	protected void cleanTbl(String tblName) {
		sqlMapper.delete("delete from " + tblName);
	}
	
	@Ignore
	protected void insert(String sql) {
		sqlMapper.insert(sql);
	}
	
	@Ignore
	protected <T> T selectOne(String sql, Class<T> clz) {
		return sqlMapper.selectOne(sql, clz);
	}
}
