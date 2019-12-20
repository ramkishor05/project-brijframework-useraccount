package com.brijframework.useraccount.beans.rqrs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.brijframework.useraccount.beans.EOUserProfileDTO;

public class UserAccountRequest {

	private long accountId;

	@NotEmpty
	private String type;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@NotNull
	@NotEmpty
	private EOUserProfileDTO userProfile;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public EOUserProfileDTO getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(EOUserProfileDTO userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "RegisterRequest [type=" + type + ", username=" + username + ", password=" + password + ", userProfile="
				+ userProfile + "]";
	}

}
