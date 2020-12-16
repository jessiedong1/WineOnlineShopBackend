package com.wineshop.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wineshop.model.ERole;
import com.wineshop.model.MailMessage;
import com.wineshop.model.Role;
import com.wineshop.model.User;
import com.wineshop.payload.request.LoginRequest;
import com.wineshop.payload.request.SignupRequest;
import com.wineshop.payload.response.JwtResponse;
import com.wineshop.payload.response.MessageResponse;
import com.wineshop.repository.RoleRepository;
import com.wineshop.repository.UserRepository;
import com.wineshop.security.jwt.JwtUtils;
import com.wineshop.security.service.UserDetailsImpl;
import com.wineshop.service.MailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	 MailService mService;
	
	@Autowired
	MailMessage message;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		message.setEmailAddress(userDetails.getEmail());
		message.setSubject(userDetails.getUsername()+" We are happy to have you back");
		message.setBodyText("Hi " + userDetails.getUsername()+"! You've signed in ");
		try {
			mService.sendEmail(message);
			}catch(MailException e) {
				System.out.println(e);
			}
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(
					ERole.CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "owner":
					System.out.println(strRoles.size());
					Role ownerRole = roleRepository.findByName(
							ERole.OWNER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ownerRole);

					break;
				case "manager":
					Role managerRole = roleRepository.findByName(
							ERole.MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(managerRole);
				case "staff":
					Role staffRole = roleRepository.findByName(
							ERole.STAFF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(staffRole);

					break;

				default:
					Role userRole = roleRepository.findByName(
							ERole.CUSTOMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		message.setEmailAddress(user.getEmail());
		message.setSubject(user.getUsername()+" We are happy to have you");
		message.setBodyText("Hi " + user.getUsername()+"! Thanks for signing up wine divine! ");
		try {
			mService.sendEmail(message);
			}catch(MailException e) {
				System.out.println(e);
			}
		
		

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
