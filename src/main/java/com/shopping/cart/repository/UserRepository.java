package com.shopping.cart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.modal.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	public Users findByUsername(String username);
	
}
