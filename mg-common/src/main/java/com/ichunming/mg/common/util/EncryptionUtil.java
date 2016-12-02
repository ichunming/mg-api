/**
 * Encryption Util
 * create by ming 2016/11/15
 * v0.1
 */
package com.ichunming.mg.common.util;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

public class EncryptionUtil {

	private static DefaultHashService hashService = new DefaultHashService();
	
	private static final int DEFAULT_ITERATIONS = 5;
	
	public static final String ALGORITHM_MD5 = "MD5";
	
	public static final String ALGORITHM_SHA = "SHA";
	
	public static final String ALGORITHM_DES = "DES";
	
	/**
	 * 数据加密:MD5
	 * @param data
	 * @param salt
	 * @return
	 */
	public static String encrypt(String data, String salt) {
		if(StringUtil.isEmpty(data) || StringUtil.isEmpty(salt)) {
			return null;
		}
        // encrypt
        return computeHash(data, salt, ALGORITHM_MD5);
	}
	
	/**
	 * 数据加密
	 * @param data
	 * @param salt
	 * @param algorithm
	 * @return
	 */
	public static String encrypt(String data, String salt, String algorithm) {
		if(StringUtil.isEmpty(data) || StringUtil.isEmpty(salt) || StringUtil.isEmpty(algorithm)) {
			return null;
		}
        // encrypt
        return computeHash(data, salt, algorithm);
	}
	
	/**
	 * 密文匹配
	 * @param orgData
	 * @param salt
	 * @param encryptData
	 * @return
	 */
	public static boolean match(Object orgData, Object salt, String encryptData) {
		if((null == orgData) || (null == salt) || StringUtil.isEmpty(encryptData)) {
			return false;
		}
		// encrypt
		String computeData = computeHash(orgData, salt, ALGORITHM_MD5);
		
		// match password
		return computeData.equals(encryptData);
	}
	
	/**
	 * 密文匹配
	 * @param orgData
	 * @param salt
	 * @param encryptData
	 * @param algorithm
	 * @return
	 */
	public static boolean match(Object orgData, Object salt, String encryptData, String algorithm) {
		if((null == orgData) || (null == salt) || StringUtil.isEmpty(encryptData) || StringUtil.isEmpty(algorithm)) {
			return false;
		}
		// encrypt
		String computeData = computeHash(orgData, salt, algorithm);
		
		// match password
		return computeData.equals(encryptData);
	}
	
	/**
	 * 密文计算
	 * @param password
	 * @param salt
	 * @param algorithm
	 * @return
	 */
	private static String computeHash(Object password, Object salt, String algorithm) {
		// create request
		HashRequest request = new HashRequest.Builder()
        		.setAlgorithmName(algorithm)
        		.setSource(ByteSource.Util.bytes(password))
        		.setSalt(ByteSource.Util.bytes(salt))
        		.setIterations(DEFAULT_ITERATIONS)
        		.build();
        
        // encrypt
        return hashService.computeHash(request).toString();
	}
}
