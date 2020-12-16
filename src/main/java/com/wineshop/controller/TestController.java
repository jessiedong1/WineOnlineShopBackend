package com.wineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wineshop.model.Order;
import com.wineshop.model.User;
import com.wineshop.repository.OrderRepository;
import com.wineshop.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
//@RequestMapping()
public class TestController {
	
	@Autowired
	UserRepository userRepo;
	@GetMapping("/home")
//	@GetMapping()
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/customer")
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('STAFF') or hasAuthority('OWNER') or hasAuthority('MANAGER')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/admin/owner")
	@PreAuthorize("hasAuthority('OWNER')")
	public List<User> ownerAccess() {
//		return "Owner Board.";
		
		return userRepo.findAll();
		
		
	}

	@GetMapping("/admin/manager")
	@PreAuthorize("hasAuthority('MANAGER') or hasAuthority('OWNER')")
	public String managerAccess() {
		return "Manager Board.";
	}
	
	@GetMapping("/admin/staff")
	@PreAuthorize("hasAuthority('STAFF') or hasAuthority('OWNER') or hasAuthority('MANAGER')")
	public String staffAccess() {
		return "Staff Board.";
	}
	
}