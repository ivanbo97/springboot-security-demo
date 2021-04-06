package com.springsecurity.course.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springsecurity.course.security.role.ApplicationUserPermission;
import com.springsecurity.course.security.role.ApplicationUserRole;


import static com.springsecurity.course.security.role.ApplicationUserRole.*;

import java.util.concurrent.TimeUnit;

import static com.springsecurity.course.security.role.ApplicationUserPermission.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	
	private final DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired
	public ApplicationSecurityConfig(DaoAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //in detail in the next section
			.authorizeRequests()
			.antMatchers("/","index","/css/*","/js/*").permitAll()
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
			
			
			/* We are not going to use them anymore because we switch to annotation based approach - @PreAuthorize
			 * , @EnableGlobalMethodSecurity(prePostEnabled = true)
			 *
			.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
			*/
			.anyRequest()
			.authenticated()
			.and()
			//for basic authentication without form
			//.httpBasic();
			//for form based authentication
			.formLogin()
			//for custom login page we can add our html page in resources/templates
			.loginPage("/login").permitAll()
			//adding page for redirection after successful login
			.defaultSuccessUrl("/courses",true)
			//pointing to the name attribute of the input field in html page
			// the default is password/username and we can omit invocation of this method
			//but we give it as an example in case we want to change the name attribute
			.passwordParameter("password")
			.usernameParameter("username")
			//Extending the cookie expiration time
			.and()
			.rememberMe()
			.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
			//giving a key for MD5 hash
			.key("somethingverysecured")
			//adding custom logout behavior
			.and()
			.logout()
				.logoutUrl("/logout")
				//Because we have disabled csrf, we are going to logout with GET request
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("remember-me","JSESSIONID")
				.logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider);
	}


    
	
}
