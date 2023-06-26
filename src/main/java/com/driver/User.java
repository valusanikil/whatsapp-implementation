package com.driver;

public class User {
    private String name;
    private String mobile;
    
    public User() {
    	
    }

	public User(String name, String mobile) {
		super();
		this.name = name;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", mobile=" + mobile + "]";
	}
    
}
