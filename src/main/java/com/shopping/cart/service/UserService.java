package com.shopping.cart.service;

import com.shopping.cart.modal.Users;

public interface UserService {

	public Users findByUsername(String username);
	public Users getLoggedinUser();
	public long getLoggedinUserId();
	
}
