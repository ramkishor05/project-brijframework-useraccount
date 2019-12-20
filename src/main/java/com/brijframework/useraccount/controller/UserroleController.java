package com.brijframework.useraccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.useraccount.beans.rqrs.UserRoleRequest;
import com.brijframework.useraccount.beans.rqrs.UserRoleResponse;
import com.brijframework.useraccount.entities.EOUserRole;
import com.brijframework.useraccount.mapper.UserRoleMapper;
import com.brijframework.useraccount.repository.UserRoleRepository;

@RestController
@RequestMapping("api/userrole")
public class UserroleController {
		
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@PostMapping
	public UserRoleResponse addRole(@RequestBody UserRoleRequest roleRequest) {
		EOUserRole eoUserRole=userRoleMapper.getUserRoleRequest(roleRequest);
		return userRoleMapper.getUserRoleResponse(userRoleRepo.save(eoUserRole));
	}
	
	@PutMapping
	public UserRoleResponse updateRole(@RequestBody UserRoleRequest roleRequest) {
		EOUserRole eoUserRole=userRoleMapper.getUserRoleRequest(roleRequest);
		return userRoleMapper.getUserRoleResponse(userRoleRepo.save(eoUserRole));
	}
	
	@GetMapping
	public List<UserRoleResponse> getUserRoleList() {
		return userRoleMapper.getUserRoleResponse(userRoleRepo.findAll());
	}
	
	@GetMapping("{id}")
	public UserRoleResponse getUserRole(@PathVariable Long id) {
		return userRoleMapper.getUserRoleResponse(userRoleRepo.findById(id).orElseGet(null));
	}
	
	@DeleteMapping("{id}")
	public boolean deleteRole(@PathVariable Long id) {
		userRoleRepo.deleteById(id);
		return true;
	}
	
	
}
