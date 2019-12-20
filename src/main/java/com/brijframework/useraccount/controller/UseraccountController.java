package com.brijframework.useraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.useraccount.beans.rqrs.UserAccountRequest;
import com.brijframework.useraccount.beans.rqrs.UserAccountResponse;
import com.brijframework.useraccount.constant.UserRole;
import com.brijframework.useraccount.entities.EOUserAccount;
import com.brijframework.useraccount.entities.EOUserProfile;
import com.brijframework.useraccount.entities.EOUserRole;
import com.brijframework.useraccount.mapper.UserLoginMapper;
import com.brijframework.useraccount.mapper.UserProfileMapper;
import com.brijframework.useraccount.repository.UserLoginRepository;
import com.brijframework.useraccount.repository.UserProfileRepository;
import com.brijframework.useraccount.repository.UserRoleRepository;

@RestController
@RequestMapping("api/useraccount")
public class UseraccountController {
	
	@Autowired
	private UserLoginRepository userLoginRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private UserProfileRepository userProfileRepo;
	
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Autowired
	private UserProfileMapper userProfileMapper;

	@PostMapping
	public UserAccountResponse addAccount(@RequestBody UserAccountRequest registerRequest) {
		EOUserProfile eoUserProfile=userProfileMapper.mapToDAO(registerRequest.getUserProfile());
		userProfileRepo.save(eoUserProfile);
		EOUserAccount eoUserLogin=userLoginMapper.getLoginUserRegisterRequest(registerRequest);
		eoUserLogin.setUserProfile(eoUserProfile);
		EOUserRole userRole=userRoleRepo.findByRoleName(UserRole.OWNER.getRole());
		eoUserLogin.setUserRole(userRole);
		userLoginRepo.save(eoUserLogin);
		return userLoginMapper.getUserAccountResponse(eoUserLogin);
	}
	
	@PutMapping
	public UserAccountResponse updateAccount(@RequestBody UserAccountRequest userAccountRequest) {
		EOUserProfile userProfile=userProfileMapper.mapToDAO(userAccountRequest.getUserProfile());
		userProfileRepo.save(userProfile);
		EOUserAccount userAccount=userLoginMapper.getLoginUserRegisterRequest(userAccountRequest);
		userAccount.setUserProfile(userProfile);
		EOUserRole userRole=userRoleRepo.findByRoleName(UserRole.OWNER.getRole());
		userAccount.setUserRole(userRole);
		userLoginRepo.save(userAccount);
		return userLoginMapper.getUserAccountResponse(userAccount);
	}
	
	@GetMapping("{id}")
	public UserAccountResponse getUserAccount(@PathVariable long id) {
		return userLoginMapper.getUserAccountResponse(userLoginRepo.findById(id).orElseThrow(()->new RuntimeException("Not found.")));
	}
	
}
