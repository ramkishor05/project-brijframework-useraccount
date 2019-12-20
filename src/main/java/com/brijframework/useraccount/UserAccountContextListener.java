package com.brijframework.useraccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.brijframework.useraccount.constant.UserRole;
import com.brijframework.useraccount.entities.EOUserRole;
import com.brijframework.useraccount.repository.UserRoleRepository;

@Component
public class UserAccountContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRoleRepository userRoleRepo;
	
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
    	for(UserRole userRole : UserRole.values()) {
    		if(userRoleRepo.findByPosition(userRole.getPosition()) ==null) {
    			userRoleRepo.saveAndFlush(new EOUserRole(userRole.getPosition(),userRole.getRole(),userRole.getRole()));
    		}
    	}
    
    }
}