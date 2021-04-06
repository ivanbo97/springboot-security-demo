package com.springsecurity.course.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springsecurity.course.entity.UserCredentials;
import com.springsecurity.course.repository.ApplicationUserCredentialsRepo;
import com.springsecurity.course.security.role.ApplicationUserRole;


import static com.springsecurity.course.security.role.ApplicationUserRole.ADMIN;
import static com.springsecurity.course.security.role.ApplicationUserRole.STUDENT;;

@Component
public class DataLoader implements CommandLineRunner {

	private final ApplicationUserCredentialsRepo credentialsRepo;
	private final PasswordEncoder passwordEncoder;
	private final String[] usernames = {"ivanbo","maria","peter","dimitar"};
	
	@Autowired
	public DataLoader(ApplicationUserCredentialsRepo credentialsRepo, PasswordEncoder passwordEncoder) {
		this.credentialsRepo = credentialsRepo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void run(String... args) throws Exception {
		
		for(int i=1;i<usernames.length;i++) {
			String username = usernames[i];
			ApplicationUserRole role;
			role = i%2 == 0 ?  ADMIN : STUDENT;
			addUsersAndCredentials(username, role);
		}
		
		
				
	}

	private void addUsersAndCredentials (String username, ApplicationUserRole role) {
		
		System.out.println("Adding credentials to DB for: " + username + " " + role);
		UserCredentials userCredentials = UserCredentials.builder()
				.username(username)
				.password(passwordEncoder.encode("password"))
				.user_role(role)
				.build();
		
		this.credentialsRepo.save(userCredentials);
	}
}
