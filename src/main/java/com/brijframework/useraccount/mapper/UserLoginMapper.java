package com.brijframework.useraccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.brijframework.useraccount.beans.EOUserLoginDTO;
import com.brijframework.useraccount.beans.rqrs.LoginRequest;
import com.brijframework.useraccount.beans.rqrs.LoginResponse;
import com.brijframework.useraccount.beans.rqrs.UserAccountRequest;
import com.brijframework.useraccount.beans.rqrs.UserAccountResponse;
import com.brijframework.useraccount.entities.EOUserAccount;

@Mapper(componentModel = "spring", implementationPackage = "com.brijframework.useraccount.mapper.impl")
public interface UserLoginMapper extends GenericMapper<EOUserAccount, EOUserLoginDTO>{

	public EOUserAccount getLoginUserLoginRequest(LoginRequest loginRequest);
	
	public LoginResponse getLoginUserLoginResponse(EOUserAccount loginRequest);
	
	public EOUserAccount getLoginUserRegisterRequest(UserAccountRequest loginRequest);
	
	@Mapping(source="userRole.roleName", target="role")
	public UserAccountResponse getUserAccountResponse(EOUserAccount loginRequest);
	
}
