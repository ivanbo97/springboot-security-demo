package com.springsecurity.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.course.entity.UserCredentials;

@Repository
public interface ApplicationUserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {

	Optional<UserCredentials> findByUsername(String username);
	
}
