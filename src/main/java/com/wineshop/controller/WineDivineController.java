package com.wineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wineshop.model.User;
import com.wineshop.model.Wine;
import com.wineshop.repository.UserRepository;
import com.wineshop.repository.WineRepository;
import com.wineshop.service.WineDivineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/wine-divine")

public class WineDivineController {
	@Autowired
	WineDivineService wineDService;
	
	@GetMapping(value = "/description",produces = "application/json" )
	public List<Wine> getSimilarWines(@RequestParam("desc") String desc, @RequestParam("region") String region){
		Wine userInput = new Wine();
		userInput.setTitle("userInput");
		userInput.setDescriptors(wineDService.SentenceToTokens(desc));
		return wineDService.getSimilarWines(userInput,region );

	}


}
