package com.ichunming.mg.common.util;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class StringUtil {
	private static final String TEMPLATE_PATH = "/com/ichunming/mg/common/template/";
	
	// 邮箱最大长度
	private static final int EMAIL_MAX_LENGTH = 50;
	
	private static final String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	private static final String MOBILE_REG = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
	
	// 不能全为数字或字母,长度8-32
	private static final String PWD_REG = "(?!^\\d+$)(?!^[a-zA-Z]+$).{8,32}";
	
	/**
	 * password check
	 * @param pwd
	 * @return
	 */
	public static boolean isPassword(String pwd) {
		if(isEmpty(pwd)) {
			return false;
		}
		
		Pattern p = Pattern.compile(PWD_REG);// complex
		Matcher m = p.matcher(pwd);
		return m.matches();
	}
	
	/**
	 * email check
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		if(email.length() > EMAIL_MAX_LENGTH) {
			return false;
		}
		Pattern p = Pattern.compile(EMAIL_REG);// complex
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
		Pattern p = Pattern.compile(MOBILE_REG);// complex

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
	
	/**
	 * load template
	 * @param path
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String loadTemplate(String tplName, Map<String, Object> param) throws Exception {
		Configuration cfg = new Configuration();
		ClassTemplateLoader loader = new ClassTemplateLoader(StringUtil.class, TEMPLATE_PATH);
		cfg.setTemplateLoader(loader);
		cfg.setDefaultEncoding("UTF-8");
		Template template = cfg.getTemplate(tplName);
		StringWriter writer = new StringWriter();
		template.process(param, writer);
		return writer.toString();
	}
}
