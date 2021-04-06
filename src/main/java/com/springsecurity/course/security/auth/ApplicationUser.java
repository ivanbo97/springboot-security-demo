package com.springsecurity.course.security.auth;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springsecurity.course.entity.UserCredentials;

public class ApplicationUser implements UserDetails {

	private Integer userId;
	private  String username;
	private  String password;
	private UserCredentials userCredentials;
	private  Set<? extends GrantedAuthority> grantedAuthorities;
	private  boolean isAccountNonExpired = true;
	private  boolean isAccountNonLocked = true;
	private  boolean isCredentialsNonExpired = true;
	private  boolean isEnabled = true;
	
	
	
	public ApplicationUser(UserCredentials userCredentials) {
		
		this.userCredentials = userCredentials;
		this.userId = userCredentials.getId();
		this.grantedAuthorities = userCredentials.getUser_role().getGrantedAuthorities();
		this.username = userCredentials.getUsername();
		this.password = userCredentials.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
