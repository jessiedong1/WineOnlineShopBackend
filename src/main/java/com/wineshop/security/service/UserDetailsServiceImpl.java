package com.wineshop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wineshop.model.User;
import com.wineshop.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found with username: "+ username ));
		return UserDetailsImpl.build(user);
	}

}
