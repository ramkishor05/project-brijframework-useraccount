package com.brijframework.useraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brijframework.useraccount.entities.EOUserRole;

@Repository
@Transactional
public interface UserRoleRepository  extends JpaRepository<EOUserRole, Long>{

	EOUserRole findByRoleName(String role);
	
	EOUserRole findByPosition(int position);

}
