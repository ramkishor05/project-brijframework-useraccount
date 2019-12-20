package com.brijframework.useraccount.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.brijframework.useraccount.beans.EOUserRoleDTO;
import com.brijframework.useraccount.beans.rqrs.UserRoleRequest;
import com.brijframework.useraccount.beans.rqrs.UserRoleResponse;
import com.brijframework.useraccount.entities.EOUserRole;

@Mapper(componentModel = "spring", implementationPackage = "com.brijframework.useraccount.mapper.impl")
public interface UserRoleMapper extends GenericMapper<EOUserRole, EOUserRoleDTO>{

	List<EOUserRole> getUserRoleRequest(List<UserRoleRequest> roleRequest);
	
	EOUserRole getUserRoleRequest(UserRoleRequest roleRequest);
	
	List<UserRoleResponse> getUserRoleResponse(List<EOUserRole> eoUserRole);
	
	UserRoleResponse getUserRoleResponse(EOUserRole eoUserRole);

}
