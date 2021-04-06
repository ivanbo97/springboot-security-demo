package com.springsecurity.course.security.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.course.dao.ApplicationUserDao;
import com.springsecurity.course.entity.UserCredentials;
import com.springsecurity.course.repository.ApplicationUserCredentialsRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserDao applicationUserDao;
	
	private final ApplicationUserCredentialsRepo credentialsRepository;
	
	@Autowired
	public ApplicationUserService(
			@Qualifier("fake") ApplicationUserDao applicationUserDao,
			ApplicationUserCredentialsRepo credentialsRepository) {
		
		this.applicationUserDao = applicationUserDao;
		this.credentialsRepository = credentialsRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("Calling method loadUserByUsername with param: " + username);
		Optional<UserCredentials> optionalUserCredentials =
				this.credentialsRepository.findByUsername(username);
		
		UserCredentials userCredentials = 
				optionalUserCredentials
				.orElseThrow(() -> new UsernameNotFoundException(username + "NOT FOUNDN"));
		
		return new ApplicationUser(userCredentials);
	}

}
