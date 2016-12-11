package com.ichunming.mg.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliConfiguration {
	@Value("#{configProperties['ali.endpoint']}")
	private String endpoint;
	@Value("#{configProperties['ali.accessKeyId']}")
	private String accessKeyId;
	@Value("#{configProperties['ali.accessKeySecret']}")
	private String accessKeySecret;
	@Value("#{configProperties['ali.sms.signName']}")
	private String smsSignName;
	@Value("#{configProperties['ali.sms.tplValidateCode']}")
	private String smsTplValidateCode;
	@Value("#{configProperties['ali.sms.tplNotifyCode']}")
	private String smsTplNotifyCode;
	@Value("#{configProperties['ali.oss.bucket.file.name']}")
	private String ossBktFileName;
	@Value("#{configProperties['ali.oss.bucket.file.url']}")
	private String ossBktFileUrl;
	@Value("#{configProperties['ali.oss.bucket.pic.name']}")
	private String ossBktPicName;
	@Value("#{configProperties['ali.oss.bucket.pic.url']}")
	private String ossBktPicUrl;
	@Value("#{configProperties['ali.oss.bucket.audio.name']}")
	private String ossBktAudioName;
	@Value("#{configProperties['ali.oss.bucket.audio.url']}")
	private String ossBktAudioUrl;
	@Value("#{configProperties['ali.oss.bucket.video.name']}")
	private String ossBktVideoName;
	@Value("#{configProperties['ali.oss.bucket.video.url']}")
	private String ossBktVideoUrl;
	@Value("#{configProperties['ali.oss.bucket.other.name']}")
	private String ossBktOtherName;
	@Value("#{configProperties['ali.oss.bucket.other.url']}")
	private String ossBktOtherUrl;
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getSmsSignName() {
		return smsSignName;
	}
	public void setSmsSignName(String smsSignName) {
		this.smsSignName = smsSignName;
	}
	public String getSmsTplValidateCode() {
		return smsTplValidateCode;
	}
	public void setSmsTplValidateCode(String smsTplValidateCode) {
		this.smsTplValidateCode = smsTplValidateCode;
	}
	public String getSmsTplNotifyCode() {
		return smsTplNotifyCode;
	}
	public void setSmsTplNotifyCode(String smsTplNotifyCode) {
		this.smsTplNotifyCode = smsTplNotifyCode;
	}
	public String getOssBktFileName() {
		return ossBktFileName;
	}
	public void setOssBktFileName(String ossBktFileName) {
		this.ossBktFileName = ossBktFileName;
	}
	public String getOssBktFileUrl() {
		return ossBktFileUrl;
	}
	public void setOssBktFileUrl(String ossBktFileUrl) {
		this.ossBktFileUrl = ossBktFileUrl;
	}
	public String getOssBktPicName() {
		return ossBktPicName;
	}
	public void setOssBktPicName(String ossBktPicName) {
		this.ossBktPicName = ossBktPicName;
	}
	public String getOssBktPicUrl() {
		return ossBktPicUrl;
	}
	public void setOssBktPicUrl(String ossBktPicUrl) {
		this.ossBktPicUrl = ossBktPicUrl;
	}
	public String getOssBktAudioName() {
		return ossBktAudioName;
	}
	public void setOssBktAudioName(String ossBktAudioName) {
		this.ossBktAudioName = ossBktAudioName;
	}
	public String getOssBktAudioUrl() {
		return ossBktAudioUrl;
	}
	public void setOssBktAudioUrl(String ossBktAudioUrl) {
		this.ossBktAudioUrl = ossBktAudioUrl;
	}
	public String getOssBktVideoName() {
		return ossBktVideoName;
	}
	public void setOssBktVideoName(String ossBktVideoName) {
		this.ossBktVideoName = ossBktVideoName;
	}
	public String getOssBktVideoUrl() {
		return ossBktVideoUrl;
	}
	public void setOssBktVideoUrl(String ossBktVideoUrl) {
		this.ossBktVideoUrl = ossBktVideoUrl;
	}
	public String getOssBktOtherName() {
		return ossBktOtherName;
	}
	public void setOssBktOtherName(String ossBktOtherName) {
		this.ossBktOtherName = ossBktOtherName;
	}
	public String getOssBktOtherUrl() {
		return ossBktOtherUrl;
	}
	public void setOssBktOtherUrl(String ossBktOtherUrl) {
		this.ossBktOtherUrl = ossBktOtherUrl;
	}
}
