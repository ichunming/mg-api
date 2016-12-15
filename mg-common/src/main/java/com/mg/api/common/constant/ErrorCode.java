package com.mg.api.common.constant;

public class ErrorCode {

	public static final Long SUCCESS = 0L;
	
	// 系统级错误码
	public static final Long ERR_SYS_INTERNAL_ERROR = 1000L;
	public static final Long ERR_SYS_SERVER_BUSY = 1001L;
	public static final Long ERR_SYS_DATABASE_ERROR = 1002L;
	public static final Long ERR_SYS_NO_RESOURCE_FOUND = 1003L;
	public static final Long ERR_SYS_METHOD_NOT_SUPPORT= 1004L;
	public static final Long ERR_SYS_BAD_REQUEST = 1005L;
	public static final Long ERR_SYS_REQUEST_PARAM_INVALID = 1006L;
	public static final Long ERR_SYS_LOAD_TPL_FAIL = 1007L;
	
	// 用户级错误码
	public static final Long ERR_USER_NO_SESSION = 2001L;
	public static final Long ERR_USER_VALIDATE_EXIST = 2002L;
	public static final Long ERR_USER_NOT_EXIST = 2003L;
	public static final Long ERR_USER_PASSWD_INVALID = 2004L;
	public static final Long ERR_USER_UNAUTHEN = 2005L;
	public static final Long ERR_USER_LOCK = 2006L;
	public static final Long ERR_USER_DELETE = 2007L;
	public static final Long ERR_USER_PORTRAIT_EMPTY = 2008L;
	public static final Long ERR_USER_VERIFY_CODE_INVALID = 2009L;
	public static final Long ERR_USER_VERIFY_CODE_OVERDUE = 2010L;
	public static final Long ERR_USER_SEND_SMS_FREQUENT = 2011L;
	public static final Long ERR_USER_UPLOAD_TYPE_LIMIT = 2012L;
	public static final Long ERR_USER_UPLOAD_SIZE_LIMIT = 2013L;
	
	// 服务级错误码
	public static final Long ERR_SVR_SMS_ERROR = 4001L;
	public static final Long ERR_SVR_EMAIL_ERROR = 4002L;
	public static final Long ERR_SVR_OSS_ERROR = 4003L;
	public static final Long ERR_SVR_FILE_ERROR = 4004L;
}
