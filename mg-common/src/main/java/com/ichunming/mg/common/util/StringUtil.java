package com.ichunming.mg.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// complex
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 
	 * 
	 * China Mobile:
	 * 134、135、136、137、138、139、150、151、157(TD)、158、159、182、183、187、188、183、140、
	 * 141、142、143、144、146、147、148、149
	 * 
	 * China Unicom: 130、131、132、152、155、156、185、186、145
	 * 
	 * China Telecom: 133、153、180、181、189、（1349卫通）
	 * 
	 * 虚拟运营商: 170 ~ 179
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");// complex

		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 判断是否为空或 null
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return (cs == null) || (cs.length() == 0);
	}
	
	/**
	 * Collection 转String
	 * @param collection
	 * @param separator
	 * @return
	 */
	public static String toString(Collection<?> collection, String separator) {
		if(null == collection) {
			return null;
		}
		
		if(collection.isEmpty()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		Iterator<?> iterator = collection.iterator();
		while(iterator.hasNext()) {
			sb.append(iterator.next().toString()).append(separator);
		}
		sb.delete(sb.length() - separator.length(), sb.length());
		
		return sb.toString();
	}
}
