package com.mg.api.model;

import java.util.Date;

public class UserProfile {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.uid
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Long uid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.nickname
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String nickname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.portrait
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String portrait;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.real_name
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String realName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.gender
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Integer gender;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.career
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String career;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.intro
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String intro;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.domain
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String domain;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.birthday
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String birthday;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.province
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Integer province;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.city
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Integer city;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.address
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.create_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user_profile.update_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	private Date updateDate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.uid
	 * @return  the value of user_profile.uid
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.uid
	 * @param uid  the value for user_profile.uid
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.nickname
	 * @return  the value of user_profile.nickname
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.nickname
	 * @param nickname  the value for user_profile.nickname
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.portrait
	 * @return  the value of user_profile.portrait
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getPortrait() {
		return portrait;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.portrait
	 * @param portrait  the value for user_profile.portrait
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setPortrait(String portrait) {
		this.portrait = portrait == null ? null : portrait.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.real_name
	 * @return  the value of user_profile.real_name
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.real_name
	 * @param realName  the value for user_profile.real_name
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.gender
	 * @return  the value of user_profile.gender
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.gender
	 * @param gender  the value for user_profile.gender
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.career
	 * @return  the value of user_profile.career
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.career
	 * @param career  the value for user_profile.career
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setCareer(String career) {
		this.career = career == null ? null : career.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.intro
	 * @return  the value of user_profile.intro
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.intro
	 * @param intro  the value for user_profile.intro
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.domain
	 * @return  the value of user_profile.domain
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.domain
	 * @param domain  the value for user_profile.domain
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setDomain(String domain) {
		this.domain = domain == null ? null : domain.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.birthday
	 * @return  the value of user_profile.birthday
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.birthday
	 * @param birthday  the value for user_profile.birthday
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.province
	 * @return  the value of user_profile.province
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Integer getProvince() {
		return province;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.province
	 * @param province  the value for user_profile.province
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setProvince(Integer province) {
		this.province = province;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.city
	 * @return  the value of user_profile.city
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Integer getCity() {
		return city;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.city
	 * @param city  the value for user_profile.city
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setCity(Integer city) {
		this.city = city;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.address
	 * @return  the value of user_profile.address
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.address
	 * @param address  the value for user_profile.address
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.create_date
	 * @return  the value of user_profile.create_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.create_date
	 * @param createDate  the value for user_profile.create_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_profile.update_date
	 * @return  the value of user_profile.update_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_profile.update_date
	 * @param updateDate  the value for user_profile.update_date
	 * @mbggenerated  Fri Dec 30 17:34:55 CST 2016
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}