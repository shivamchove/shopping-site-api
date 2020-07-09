package com.shopping.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.shopping.cart.modal.Users;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Users getLoggedinUser() {
		User user=  ((User) SecurityContextHolder.getContext()
	            .getAuthentication()
	            .getPrincipal());
		if(user!=null) {
			return userRepository.findByUsername(user.getUsername());
		}
		return null;		
	}
	public long getLoggedinUserId() {
		Users users= this.getLoggedinUser();
		if(users!=null) {
			return users.getId();
		}
		return 0;
	}

}
