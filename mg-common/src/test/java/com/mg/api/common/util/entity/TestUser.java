package com.mg.api.common.util.entity;

public class TestUser {
	private String id;
	private String name;
	private String password;

	public TestUser() {}
	
	public TestUser(String name, String password) {
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
