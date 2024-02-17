package com.virtusa.querymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()	
		.loginPage("/login")
		.defaultSuccessUrl("/")
		.successForwardUrl("/")
		.permitAll()
		.and()
		.authorizeRequests().antMatchers("/user/**").authenticated()
		.and()
		.authorizeRequests().antMatchers("/query/**").authenticated()
		.and()
		.authorizeRequests().antMatchers("/api/**").authenticated()
		.and()
		.authorizeRequests().antMatchers("/comment/**").authenticated();
		http.csrf().disable();
		http.logout().logoutSuccessUrl("/login").permitAll();
	}
	
	

}
