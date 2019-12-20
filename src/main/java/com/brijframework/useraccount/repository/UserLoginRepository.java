package com.brijframework.useraccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brijframework.useraccount.entities.EOUserAccount;

@Repository
@Transactional
public interface UserLoginRepository  extends JpaRepository<EOUserAccount, Long>{

	@Query("select u from EOUserAccount u where u.username = :username")
	Optional<EOUserAccount> findUserName(@Param("username")String username);

}
