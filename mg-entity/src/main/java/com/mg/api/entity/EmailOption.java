package com.mg.api.entity;

public class EmailOption {
	private String host;

	private String username;

	private String password;

	private String from;

	private String fromName;

	private String charset;

	public EmailOption() {}
	
	public EmailOption(String host, String username, String password, String from, String fromName, String charset) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.from = from;
		this.fromName = fromName;
		this.charset = charset;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
}
