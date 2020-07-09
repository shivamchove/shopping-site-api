package com.shopping.cart.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.config.JwtTokenUtil;
import com.shopping.cart.config.MyUserDetailService;
import com.shopping.cart.modal.Users;
import com.shopping.cart.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager; 

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> userLogin(@RequestBody HashMap<String, String> vals) throws Exception {
		String username=vals.get("username");
		String password=vals.get("password");
		System.out.println(password+" :: "+bCryptPasswordEncoder.encode(password));
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			final UserDetails userDetails=myUserDetailService.loadUserByUsername(username);
			final String token=jwtTokenUtil.generateToken(userDetails);
			Users user= userService.findByUsername("admin");
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("user_data", user);
			map.put("user_token", token);
			return ResponseEntity.ok().body(map);
			
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials",e);
		}
	}
	
	@RequestMapping(value = "/get-user", method = RequestMethod.GET)
	public Users getUser() {
		Users user= userService.findByUsername("admin");
		return user;
	}
	
}
