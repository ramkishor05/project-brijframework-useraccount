package com.brijframework.useraccount.constant;

public enum UserType {

	CUSTOMER("CUSTOMER"), VENDOR("VENDOR"), SUPPLIER("SUPPLIER");
	
	private String type;

	private UserType(String type) {
		this.type=type;
	}

	public static boolean contains(String type) {
		for(UserType userType:values()) {
			if(userType.type.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	public static UserType find(String type) {
		for(UserType userType:values()) {
			if(userType.type.equalsIgnoreCase(type)) {
				return userType;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return type.toString();
	}
}
