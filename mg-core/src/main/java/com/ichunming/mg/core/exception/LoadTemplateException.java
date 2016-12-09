package com.ichunming.mg.core.exception;

public class LoadTemplateException extends BaseException {
	private static final long serialVersionUID = -3005611473850038334L;

	public LoadTemplateException(Throwable cause) {
		super(cause);
	}

	public LoadTemplateException(Throwable cause, String code) {
		super(cause, code);
	}
	
	public LoadTemplateException(String code, String message) {
		super(code, message);
	}

	public LoadTemplateException(Throwable cause, String code, String message) {
		super(cause, code, message);
	}
}
