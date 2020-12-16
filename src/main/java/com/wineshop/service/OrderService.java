package com.wineshop.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wineshop.exception.RecordNotFoundException;
import com.wineshop.model.Address;
import com.wineshop.model.Order;
import com.wineshop.model.OrderProduct;
import com.wineshop.model.OrderProductDto;
import com.wineshop.model.OrderStatus;
import com.wineshop.repository.AddressRepository;
import com.wineshop.repository.OrderProductRepository;
import com.wineshop.repository.OrderRepository;
import com.wineshop.repository.UserRepository;
import com.wineshop.repository.WineRepository;

@Service
public class OrderService{
	@Autowired
	WineRepository wineRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderProductRepository orderProductRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	
	@Transactional 
	public Order insertOrder( List<OrderProductDto> productOrders, Address address, long id){
		  	Order order = new Order();
	        order.setUser(userRepo.findById(id).get());
	        order.setStatus(OrderStatus.PAID.name());
	        order = orderRepo.save(order);
	        List<OrderProduct> orderProducts = new ArrayList<>();
	        for (OrderProductDto dto : productOrders) {
	            orderProducts.add(orderProductRepo.save(new OrderProduct(order,wineRepo.findById(dto.getProduct().getId()).get(),dto.getQuantity())));
	        }

	        order.setOrderProducts(orderProducts);
	        order.setDateCreated(LocalDate.now());
		 addressRepo.save(address);
		 order.setAddress(address);
		 orderRepo.save(order);
		
		
		
		return order;
	}
	
	  public boolean validateProductsExistence(List<OrderProductDto> orderProducts) throws RecordNotFoundException {
	  List<OrderProductDto> list = orderProducts
	    .stream()
	    .filter(op -> Objects.isNull(wineRepo.findById(op
	      .getProduct()
	      .getId())))
	    .collect(Collectors.toList());
	
	  if (!CollectionUtils.isEmpty(list)) {
	      throw new RecordNotFoundException("Product not found");
	    
	     
	  }
	  
	  return true;
	}
	
}
