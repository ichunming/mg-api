package com.ichunming.mg.core.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConfiguration {
	@Value("#{configProperties['oss.endpoint']}")
	private String endpoint;
	@Value("#{configProperties['oss.accessKeyId']}")
	private String accessKeyId;
	@Value("#{configProperties['oss.accessKeySecret']}")
	private String accessKeySecret;
	@Value("#{configProperties['sms.signName']}")
	private String signName;
	@Value("#{configProperties['sms.tplValidateCode']}")
	private String tplValidateCode;
	@Value("#{configProperties['sms.tplNotifyCode']}")
	private String tplNotifyCode;
	@Value("#{configProperties['oss.bucket.pic.name']}")
	private String btPicName;
	@Value("#{configProperties['oss.bucket.pic.url']}")
	private String btPicUrl;
	@Value("#{configProperties['oss.bucket.audio.name']}")
	private String btAudioName;
	@Value("#{configProperties['oss.bucket.audio.url']}")
	private String btAudioUrl;
	@Value("#{configProperties['oss.bucket.video.name']}")
	private String btVideoName;
	@Value("#{configProperties['oss.bucket.video.url']}")
	private String btVideoUrl;
	
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
	public String getBtPicName() {
		return btPicName;
	}
	public void setBtPicName(String btPicName) {
		this.btPicName = btPicName;
	}
	public String getBtPicUrl() {
		return btPicUrl;
	}
	public void setBtPicUrl(String btPicUrl) {
		this.btPicUrl = btPicUrl;
	}
	public String getBtAudioName() {
		return btAudioName;
	}
	public void setBtAudioName(String btAudioName) {
		this.btAudioName = btAudioName;
	}
	public String getBtAudioUrl() {
		return btAudioUrl;
	}
	public void setBtAudioUrl(String btAudioUrl) {
		this.btAudioUrl = btAudioUrl;
	}
	public String getBtVideoName() {
		return btVideoName;
	}
	public void setBtVideoName(String btVideoName) {
		this.btVideoName = btVideoName;
	}
	public String getBtVideoUrl() {
		return btVideoUrl;
	}
	public void setBtVideoUrl(String btVideoUrl) {
		this.btVideoUrl = btVideoUrl;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getTplValidateCode() {
		return tplValidateCode;
	}
	public void setTplValidateCode(String tplValidateCode) {
		this.tplValidateCode = tplValidateCode;
	}
	public String getTplNotifyCode() {
		return tplNotifyCode;
	}
	public void setTplNotifyCode(String tplNotifyCode) {
		this.tplNotifyCode = tplNotifyCode;
	}
}
