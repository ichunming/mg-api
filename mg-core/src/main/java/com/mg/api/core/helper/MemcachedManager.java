/**
 * @author ming
 * @date 2017年1月25日 下午3:13:01
 */
package com.mg.api.core.helper;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mg.api.common.util.StringUtil;
import com.mg.api.core.cache.ICacheManager;

import net.spy.memcached.MemcachedClient;

@Component
public class MemcachedManager implements ICacheManager {

	private static final Logger logger = LoggerFactory.getLogger(MemcachedManager.class);
	
	private static final String DOMAIN = "127.0.0.1";
	
	private static final int PORT = 11211;
	
	private static final String DEFAULT_REGION = "MG-REGION-DEFAULT";
	
	private static final Integer DEFAULT_EXPIRES = 60 * 60 * 24 * 30;
	
	private static final String REGION_DELIMITER = ".";
	
	private static MemcachedClient memcache;
	
	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#init()
	 */
	@Override
	public synchronized void init() throws Exception {
		if(null == memcache) {
			logger.debug("connect to memcached server...");
			memcache = new MemcachedClient(new InetSocketAddress(DOMAIN, PORT));
			logger.debug("connect to memcached server successful.");
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#shutdown()
	 */
	@Override
	public void shutdown() throws Exception {
		if(null != memcache) {
			logger.debug("shutdown memcached client...");
			memcache.shutdown();
			memcache = null;
			logger.debug("shutdown memcached client successful.");
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(String key, Object value) throws Exception {
		put(DEFAULT_REGION, key, value, DEFAULT_EXPIRES);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#put(java.lang.Object, java.lang.Object, java.lang.Integer)
	 */
	@Override
	public void put(String key, Object value, Integer expires) throws Exception {
		put(DEFAULT_REGION, key, value, expires);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#put(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(String region, String key, Object value) throws Exception {
		put(region, key, value, DEFAULT_EXPIRES);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#put(java.lang.String, java.lang.Object, java.lang.Object, java.lang.Integer)
	 */
	@Override
	public void put(String region, String key, Object value, Integer expires) throws Exception {
		init();
		
		memcache.set(getFullKey(region, key), expires, value);
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#get(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object get(String region, String key) throws Exception {
		init();
		
		return memcache.get(getFullKey(region, key));
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#get(java.lang.Object)
	 */
	@Override
	public Object get(String key) throws Exception {
		init();
		
		return memcache.get(getFullKey(DEFAULT_REGION, key));
	}

	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#remove(java.lang.String, java.lang.Object)
	 */
	@Override
	public void remove(String region, String key) throws Exception {
		init();
		
		memcache.delete(getFullKey(region, key));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.api.core.cache.CacheManager#remove(java.lang.Object)
	 */
	@Override
	public void remove(String key) throws Exception {
		init();
		
		memcache.delete(getFullKey(DEFAULT_REGION, key));
	}
	
	private String getFullKey(String region, Object key) {
		if(StringUtil.isEmpty(region)) {
			throw new NullPointerException("region can not be empty.");
		}
		
		if(null == key) {
			throw new NullPointerException("key can not be empty.");
		}
		
		return region + REGION_DELIMITER + key;
	}
}