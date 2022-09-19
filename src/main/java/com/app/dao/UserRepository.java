package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.email=:em and u.password=:pass")
	User authenticateUser(@Param("em")String em, @Param("pass")String pass);
	
	@Query("select u from User u where u.user_category=:uc")
	List<User>findByUser_Category(@Param("uc")String uc);
	
	

}
