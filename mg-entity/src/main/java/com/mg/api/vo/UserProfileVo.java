package com.mg.api.vo;

import com.mg.api.common.util.DateUtil;
import com.mg.api.common.util.StringUtil;
import com.mg.api.entity.UserView;
import com.mg.api.model.UserProfile;

public class UserProfileVo {
	private String nickname;
	private static final int NICKNAME_LEN = 50;
	
	private String portrait;
	
	private String realName;
	private static final int REALNAME_LEN = 50;
	
	private int gender;
	
	private String career;
	private static final int CAREER_LEN = 50;
	
	private String intro;
	private static final int INTRO_LEN = 50;
	
	private String birthday;
	
	private int provinceId;
	
	private String province;
	
	private int cityId;
	
	private String city;
	
	private String address;
	private static final int ADDRESS_LEN = 200;
	
	public String check() {
		// nickname
		if(!StringUtil.isEmpty(nickname) && nickname.length() > NICKNAME_LEN) return "nickname too long";
		// realName
		if(!StringUtil.isEmpty(realName) && realName.length() > REALNAME_LEN) return "real name too long";
		// province TODO
		
		// city TODO
		
		// career
		if(!StringUtil.isEmpty(career) && career.length() > CAREER_LEN) return "career too long";
		// intro
		if(!StringUtil.isEmpty(intro) && intro.length() > INTRO_LEN) return "intro too long";
		// birthday
		if(!StringUtil.isEmpty(birthday) && DateUtil.parseDate(birthday) == null) return "birthday format wrong";
		// address
		if(!StringUtil.isEmpty(address) && address.length() > ADDRESS_LEN) return "address too long";
		
		return null;
	}
	
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public UserProfile toUserProfile() {
		UserProfile profile = new UserProfile();
		profile.setNickname(nickname);
		profile.setPortrait(portrait);
		profile.setRealName(realName);
		profile.setBirthday(birthday);
		profile.setProvince(provinceId);
		profile.setCity(cityId);
		profile.setAddress(address);
		
		return profile;
	}
	
	public void fromViews(UserView view) {
		this.nickname = view.getNickname();
		this.portrait = view.getPortrait();
		this.realName = view.getRealName();
		this.birthday = view.getBirthday();
		this.province = view.getProvince();
		this.city = view.getCity();
		this.address = view.getAddress();
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

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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
}
