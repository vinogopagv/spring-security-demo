package com.example.securitydemo.config;

import static com.example.securitydemo.config.ApplicationUserRole.ADMIN;
import static com.example.securitydemo.config.ApplicationUserRole.ADMIN_TRAINEE;
import static com.example.securitydemo.config.ApplicationUserRole.STUDENT;
import static com.example.securitydemo.config.ApplicationuserPermission.COURSE_WRITE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
/**
 * Configuration file for main security related settings
 * @author Vinothkumar
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*").permitAll()
		.antMatchers("/controller/**").hasRole(STUDENT.name())
		.antMatchers(HttpMethod.DELETE,"management/controller/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers(HttpMethod.POST,"management/controller/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers(HttpMethod.PUT,"management/controller/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers(HttpMethod.GET,"management/controller/**").hasAnyRole(ADMIN.name(),ADMIN_TRAINEE.name())
		.anyRequest()
		.authenticated().and().httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails useStudent = User.builder()
				.username("gvstudent")
				.password(passwordEncoder.encode("student123"))
				.roles(STUDENT.name()).build();

		UserDetails userAdmin = User.builder()
				.username("gvadmin")
				.password(passwordEncoder.encode("admin123"))
				.roles(ADMIN.name()).build();

		UserDetails traineeAdmin = User.builder()
				.username("gvtraineeadmin")
				.password(passwordEncoder.encode("admin123"))
				.roles(ADMIN_TRAINEE.name()).build();

		return new InMemoryUserDetailsManager(useStudent,userAdmin,traineeAdmin);
	}



}
