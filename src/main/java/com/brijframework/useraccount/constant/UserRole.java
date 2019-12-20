package com.brijframework.useraccount.constant;

public enum UserRole {

	OWNER("OWNER",1), MANAGER("MANAGER",2), SUPERVISOR("SUPERVISOR",3), CREW("CREW",4);
	 
	String role;
	int position;
	
	private UserRole(String role, int position) {
		this.role = role;
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public int getPosition() {
		return position;
	}
	
}
