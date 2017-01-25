/**
 * @author ming
 * @date 2017年1月25日 下午12:00:54
 */
package com.mg.api.core.cache;

public interface ICacheManager {
	public void init() throws Exception;
	
	public void shutdown() throws Exception;

	public void put(String key, Object value) throws Exception;
	
	public void put(String key, Object value, Integer expires) throws Exception;
	
	public void put(String region, String key, Object value) throws Exception;
	
	public void put(String region, String key, Object value, Integer expires) throws Exception;
	
	public Object get(String key) throws Exception;
	
	public Object get(String region, String key) throws Exception;

	public void remove(String key) throws Exception;
	
	public void remove(String region, String key) throws Exception;
}