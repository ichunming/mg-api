package com.mg.api.core.exception;

public class SmsServiceException extends BaseException {
	private static final long serialVersionUID = -1316147934221784889L;

	public SmsServiceException(Throwable cause) {
		super(cause);
	}

	public SmsServiceException(Throwable cause, String code) {
		super(cause, code);
	}
	
	public SmsServiceException(String code, String message) {
		super(code, message);
	}

	public SmsServiceException(Throwable cause, String code, String message) {
		super(cause, code, message);
	}
}
