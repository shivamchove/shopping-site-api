package com.shopping.cart.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.cart.modal.Users;
import com.shopping.cart.service.UserService;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=userService.findByUsername(username);
		try {
			String uName=user.getUsername();
			String password=user.getPassword();
			List<GrantedAuthority> grantedAuthorities=user.getUserRoles().stream()
					.map(role->{
						SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(role.getRole());
						return simpleGrantedAuthority;
					}
				).collect(Collectors.toList());
			
			return new User(uName,password, grantedAuthorities);
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("User with username '"+username+"' not found.");
		}
	}	
}
