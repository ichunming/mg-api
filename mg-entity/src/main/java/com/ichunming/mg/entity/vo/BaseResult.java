package com.ichunming.mg.entity.vo;

public class BaseResult {
	private Long code;
	private Object data;
	
	public BaseResult(Long code) {
		this.code = code;
		this.data = null;
	}
	
	public BaseResult(Long code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
