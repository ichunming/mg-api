package com.ichunming.mg.core.exception;

public class EmailServiceException extends BaseException {
	private static final long serialVersionUID = 6983921653548319342L;

	public EmailServiceException(Throwable cause) {
		super(cause);
	}

	public EmailServiceException(Throwable cause, String code) {
		super(cause, code);
	}
	
	public EmailServiceException(String code, String message) {
		super(code, message);
	}

	public EmailServiceException(Throwable cause, String code, String message) {
		super(cause, code, message);
	}
}
