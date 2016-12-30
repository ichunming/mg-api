package com.mg.api.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mg.api.model.Resource;
import com.mg.api.vo.ResourceVo;

public class ResourceDaoTest extends BaseTest {
	@Before
	public void setUp() throws Exception {
		cleanTbl("resource");
	}
	
	@Test
	public void insertTest() throws Exception {
		// create component
		ResourceDao resourceDao = sqlSession.getMapper(ResourceDao.class);

		// prepare data
		List<Resource> resources = preResources(1);
		
		// test method
		resourceDao.insert(resources.get(0));
		
		// verify result
		Integer count = selectOne("select COUNT(1) from resource", Integer.class);
		assertEquals(count.intValue(), 1);
	}
	
	@Test
	public void batchInsertTest() throws Exception {
		// create component
		ResourceDao resourceDao = sqlSession.getMapper(ResourceDao.class);

		// prepare data
		List<Resource> resources = preResources(3);
		
		// test method
		resourceDao.batchInsert(resources);
		
		// verify result
		Integer count = selectOne("select COUNT(1) from resource", Integer.class);
		assertEquals(count.intValue(), 3);
	}
	
	@Test
	public void getByIdsTest() throws Exception {
		// create component
		ResourceDao resourceDao = sqlSession.getMapper(ResourceDao.class);

		// prepare data
		List<Resource> resources = preResources(3);
		resourceDao.batchInsert(resources);
		
		// test method
		List<Resource> result = resourceDao.getByIds(Arrays.asList(resources.get(0).getId(), resources.get(1).getId(), resources.get(2).getId()));
		
		// verify result
		assertEquals(result.size(), 3);
	}
	
	
	@Test
	public void deleteByIdsTest() throws Exception {
		// create component
		ResourceDao resourceDao = sqlSession.getMapper(ResourceDao.class);

		// prepare data
		List<Resource> resources = preResources(3);
		resourceDao.batchInsert(resources);
		
		// test method
		resourceDao.deleteByIds(Arrays.asList(resources.get(0).getId(), resources.get(1).getId(), resources.get(2).getId()));
		
		// verify result
		Integer count = selectOne("select COUNT(1) from resource", Integer.class);
		assertEquals(count.intValue(), 0);
	}
	
	@Test
	public void getByTimeFilterTest() throws Exception {
		// create component
		ResourceDao resourceDao = sqlSession.getMapper(ResourceDao.class);

		// prepare data
		List<Resource> resources = preResources(3);
		resourceDao.batchInsert(resources);
		
		// test method
		List<ResourceVo> result = resourceDao.getByTimeFilter(1L, 1);
		
		// verify result
		assertEquals(result.size(), 3);
	}
	
	@Ignore
	private List<Resource> preResources(int num) {
		List<Resource> resources = new ArrayList<Resource>();
		Resource resource = null;
		Date current = new Date();
		for(int i = 0; i < num; i++) {
			resource = new Resource();
			resource.setUid(1L);
			resource.setId("TEST_ID_" + i);
			resource.setType(1);
			resource.setUseCnt(0);
			resource.setSize(1000L);
			resource.setCreateDate(current);
			
			resources.add(resource);
		}
		
		return resources;
	}
}
