package com.brijframework.useraccount.beans;

public class EOUserLoginDTO {

	private long id;

	private String username;

	private String password;
	
	private EOUserRoleDTO role;
	
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public EOUserRoleDTO getRole() {
		return role;
	}

	public void setRole(EOUserRoleDTO role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
