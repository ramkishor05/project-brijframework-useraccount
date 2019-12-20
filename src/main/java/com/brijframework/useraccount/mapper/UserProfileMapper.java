package com.brijframework.useraccount.mapper;

import org.mapstruct.Mapper;

import com.brijframework.useraccount.beans.EOUserProfileDTO;
import com.brijframework.useraccount.entities.EOUserProfile;

@Mapper(componentModel = "spring", implementationPackage = "com.brijframework.useraccount.mapper.impl")
public interface UserProfileMapper  extends GenericMapper<EOUserProfile, EOUserProfileDTO>{

}
