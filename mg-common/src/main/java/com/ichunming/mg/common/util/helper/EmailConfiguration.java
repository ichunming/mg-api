package com.ichunming.mg.common.util.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfiguration {
	@Value("#{configProperties['mail.host']}")
	private String host;
	@Value("#{configProperties['mail.username']}")
	private String username;
	@Value("#{configProperties['mail.password']}")
	private String password;
	@Value("#{configProperties['mail.from']}")
	private String from;
	@Value("#{configProperties['mail.fromName']}")
	private String fromName;
	@Value("#{configProperties['mail.charset']}")
	private String charset;
	
	private String verifyCodeSubject = "帐号验证";
	private String verifyCodeMsgTpl = "emailVerifyCodeTpl.xml";
	
	public String getVerifyCodeSubject() {
		return verifyCodeSubject;
	}
	public void setVerifyCodeSubject(String verifyCodeSubject) {
		this.verifyCodeSubject = verifyCodeSubject;
	}
	public String getVerifyCodeMsgTpl() {
		return verifyCodeMsgTpl;
	}
	public void setVerifyCodeMsgTpl(String verifyCodeMsgTpl) {
		this.verifyCodeMsgTpl = verifyCodeMsgTpl;
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
