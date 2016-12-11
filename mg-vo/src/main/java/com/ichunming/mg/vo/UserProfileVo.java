package com.ichunming.mg.vo;

import com.ichunming.mg.common.util.DateUtil;
import com.ichunming.mg.common.util.StringUtil;
import com.ichunming.mg.entity.UserView;
import com.ichunming.mg.model.UserProfile;


public class UserProfileVo {
	private String nickname;
	private static final int NICKNAME_LEN = 50;
	
	private String portrait;
	
	private String realName;
	private static final int REALNAME_LEN = 50;
	
	private String birthday;
	
	private String province;
	private static final int PROVINCE_LEN = 20;
	
	private String city;
	private static final int CITY_LEN = 20;
	
	private String address;
	private static final int ADDRESS_LEN = 200;
	
	public String check() {
		// nickname
		if(!StringUtil.isEmpty(nickname) && nickname.length() > NICKNAME_LEN) return "nickname too long";
		// realName
		if(!StringUtil.isEmpty(realName) && realName.length() > REALNAME_LEN) return "real name too long";
		// birthday
		if(!StringUtil.isEmpty(birthday) && DateUtil.parseDate(birthday) == null) return "birthday format is wrong";
		// province
		if(!StringUtil.isEmpty(province) && province.length() > PROVINCE_LEN) return "province too long"; 
		// city
		if(!StringUtil.isEmpty(city) && city.length() > CITY_LEN) return "city too long";
		// address
		if(!StringUtil.isEmpty(address) && address.length() > ADDRESS_LEN) return "address too long";
		
		return null;
	}
	
	public UserProfile toUserProfile() {
		UserProfile profile = new UserProfile();
		profile.setNickname(nickname);
		profile.setPortrait(portrait);
		profile.setRealName(realName);
		profile.setBirthday(birthday);
		profile.setProvince(province);
		profile.setCity(city);
		profile.setAddress(address);
		
		return profile;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void fromView(UserView view) {
		this.nickname = view.getNickname();
		this.portrait = view.getPortrait();
		this.realName = view.getRealName();
		this.birthday = view.getBirthday();
		this.province = view.getProvince();
		this.city = view.getCity();
		this.address = view.getAddress();
	}
}
