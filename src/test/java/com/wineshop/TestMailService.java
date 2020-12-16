package com.wineshop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import static org.hamcrest.CoreMatchers.any;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wineshop.controller.AuthController;
import com.wineshop.model.MailMessage;
import com.wineshop.model.User;
import com.wineshop.payload.request.LoginRequest;
import com.wineshop.payload.request.SignupRequest;
import com.wineshop.repository.UserRepository;
import com.wineshop.security.WebSecurityConfig;
import com.wineshop.security.jwt.AuthTokenFilter;
import com.wineshop.security.jwt.JwtUtils;
import com.wineshop.service.MailService;

@WebMvcTest(controllers= AuthController.class)
@ExtendWith(MockitoExtension.class)
class TestMailService {
		@Autowired
		private MockMvc mockMvc;
		
		@Autowired
		 ObjectMapper objectMapper;
		
		@MockBean
		 AuthController authController;
		
		@MockBean
	UserRepository userRepo;
		
		@MockBean
		UserDetailsService userDetailsService;
		
		@MockBean 
		 WebSecurityConfig webSecurityConfig;
		
		@MockBean
		AuthTokenFilter authTokenFilter;
		@MockBean
		AuthenticationManager authenticationManager;
		@MockBean
		JwtUtils jwtUtils;
		@MockBean
		AuthenticationEntryPoint unauthorizedHandler;
		@MockBean
		SecurityContext securityContext;
		
		@MockBean
		PasswordEncoder passwordEncoder;
		
		
		
		
		 private List<User> userList;

//	    @Before
	    public void before() {
	    	this.userList = new ArrayList<>();                                    
	    	this.userList.add(new User(1L, "pwd1","user1@gmail.com","User1"));                               
	    	this.userList.add(new User(2L, "pwd2", "user2@gmail.com","User2"));                              
	    	this.userList.add(new User(3L, "pwd3", "user3@gmail.com","User3"));                                                       
	    	 
	    }
	    

	    @Test
	    public void emailTest() throws Exception {
	    	SignupRequest user = new SignupRequest();
	    	user.setEmail("user1@gmail.com");
	    	user.setUsername("user1");
	    	user.setPassword("User1");
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/whineshop/auth/signup")
	    			.contentType("application.json")
	    			.content(objectMapper.writeValueAsString(user));
	    	mockMvc.perform(requestBuilder)
	    			.andExpect(status().isOk());

	    
	        
	        
	    }





}
