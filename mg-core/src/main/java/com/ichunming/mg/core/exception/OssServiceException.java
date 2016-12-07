package com.ichunming.mg.core.exception;

public class OssServiceException extends BaseException {
	private static final long serialVersionUID = 6084545668254691001L;

	public OssServiceException(Throwable cause) {
		super(cause);
	}

	public OssServiceException(Throwable cause, String code) {
		super(cause, code);
	}
	
	public OssServiceException(String code, String message) {
		super(code, message);
	}

	public OssServiceException(Throwable cause, String code, String message) {
		super(cause, code, message);
	}
}
