package com.springsecurity.course.dao;

import java.util.Optional;

import com.springsecurity.course.security.auth.ApplicationUser;

public interface ApplicationUserDao {

	public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
