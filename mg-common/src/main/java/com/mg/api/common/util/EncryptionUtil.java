/**
 * Encryption Util
 * create by ming 2017/02/08
 */
package com.mg.api.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtil {
    
	/**
	 * 数据加密:base64
	 * @param data
	 * @return
	 */
	public static String base64(String data){
		if(StringUtil.isEmpty(data)) {
			return null;
		}
		
		return Base64.encodeBase64String(data.getBytes());
    }
	
	/**
	 * 数据加密:MD5
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		if(StringUtil.isEmpty(data)) {
			return null;
		}
		
        // encrypt
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 数据加密:sha1
	 * @param data
	 * @return
	 */
	public static String sha1(String data) {
		if(StringUtil.isEmpty(data)) {
			return null;
		}
		
        // encrypt
		return DigestUtils.sha1Hex(data);
	}

	/**
	 * 数据加密:sha256
	 * @param data
	 * @return
	 */
	public static String sha256(String data) {
		if(StringUtil.isEmpty(data)) {
			return null;
		}
		
        // encrypt
		return DigestUtils.sha256Hex(data);
	}
	
	/**
	 * 数据加密
	 * @param data
	 * @return
	 */
	public static String encode(String data) {
		return EncryptionUtil.md5(data);
	}
	
	/**
	 * 密文匹配:默认算法-MD5
	 * @param data
	 * @param encryptData
	 * @return
	 */
	public static boolean match(String data, String encryptData) {
		if((StringUtil.isEmpty(data)) || StringUtil.isEmpty(encryptData)) {
			return false;
		}
		
		// match password
		return encryptData.equals(EncryptionUtil.encode(data));
	}
}
