package com.springsecurity.course.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.springsecurity.course.entity.Student;
import com.springsecurity.course.security.auth.ApplicationUser;
import static com.springsecurity.course.security.role.ApplicationUserRole.STUDENT;
import static com.springsecurity.course.security.role.ApplicationUserRole.ADMIN;
import static com.springsecurity.course.security.role.ApplicationUserRole.ADMINTRAINEE;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		
	/*	List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
				"annasmith",
				passwordEncoder.encode("password"),
				STUDENT.getGrantedAuthorities(),
				true,
				true,
				true,
				true
				),
				new ApplicationUser(
						"linda",
						passwordEncoder.encode("password"),
						ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true
						),
				new ApplicationUser(
						"tom",
						passwordEncoder.encode("password"),
						ADMINTRAINEE.getGrantedAuthorities(),
						true,
						true,
						true,
						true
						)
			);
		*/
		//return applicationUsers;
		
		return null;
	}

}
