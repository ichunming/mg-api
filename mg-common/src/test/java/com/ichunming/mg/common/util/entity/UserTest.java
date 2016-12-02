package com.ichunming.mg.common.util.entity;

public class UserTest {
	private String id;
	private String name;
	private String password;

	public UserTest() {}
	
	public UserTest(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "name:" + name + " password:" + password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
