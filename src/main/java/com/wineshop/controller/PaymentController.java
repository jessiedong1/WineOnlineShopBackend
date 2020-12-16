package com.wineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Charge;
import com.wineshop.model.Address;
import com.wineshop.model.OrderProductDto;
import com.wineshop.model.StripeClient;

@CrossOrigin
@RestController
@RequestMapping("customer/payment")
public class PaymentController {

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public ResponseEntity<Object> chargeCard(@RequestBody OrderForm request) throws Exception {
        String token = request.token;
        Double amount = Double.parseDouble(request.amount);
        HttpHeaders headers = new HttpHeaders();
        try {
        	Charge charge= this.stripeClient.chargeCreditCard(token, amount);
        	return new ResponseEntity<>(charge, headers, HttpStatus.OK);
        }catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        	
        }
//        return null;
//        return this.stripeClient.chargeCreditCard(token, amount);
    }
    
    
    public static class OrderForm {
       public String token;
       public String amount;

    }
}
