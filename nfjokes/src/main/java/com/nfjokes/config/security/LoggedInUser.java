package com.nfjokes.config.security;

import org.springframework.security.core.Authentication;

import com.nfjokes.model.User;

public class LoggedInUser {
	
	public static User getUser(Authentication authentication) {
		User user = null;
		try {
			user = (User) authentication.getPrincipal();
			return user;
		} catch(Exception e) {
			return null;
		}
	}
}
