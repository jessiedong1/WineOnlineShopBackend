package com.wineshop;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wineshop.controller.AuthController;
import com.wineshop.controller.WineDivineController;
import com.wineshop.model.Wine;
import com.wineshop.repository.WineRepository;
import com.wineshop.service.WineDivineService;

@WebMvcTest(controllers= WineDivineController.class)
//@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TestWineDivineService {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WineDivineService wineDivineService;
	
	@MockBean
	WineRepository wineRepo;
	
	
	
	@Test
	public void test() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wineshop/wine-divine/description")
		.param("desc", "sweet and dry")
		.param("region", "");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk());
	}

}
