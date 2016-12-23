package com.mg.api.common.constant;

import java.io.File;

public class SystemSettings {
	// 开发模式
	public static boolean DEBUG_MODE = true;
	
	// 临时目录
	public static final String TEMP_DIR = "temp" + File.separator;
	
	// 默认头像
	public static final String DEFAULT_PORTRAIT = "";
	
	// 手机注册支持
	public static boolean MOBIL_REG_SUPPORT = true;
	
	// 邮箱注册支持
	public static boolean EMAIL_REG_SUPPORT = true;
}
