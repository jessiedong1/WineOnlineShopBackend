package com.wineshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
import com.wineshop.service.OrderService;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping()
public class OrderController {
//	@Autowired
//    WineRepository productService;
//	@Autowired
//    OrderRepository orderService;
//	@Autowired
//    OrderProductRepository orderProductService;
//	@Autowired
//	UserRepository userRepo;
//	@Autowired
//	AddressRepository addressRepo;
	
	@Autowired
	OrderService orderService;
	
	
	 @PostMapping("customer/{id}/place-order")
	 public ResponseEntity<Boolean> createOrder(@RequestBody OrderForm form, @PathVariable long id){
		 List<OrderProductDto> productOrders = form.productOrders;
		 Address address = form.address;
//		  Order order = new Order();
//	        order.setUser(userRepo.findById(id).get());
//	        order.setStatus(OrderStatus.PAID.name());
//	        order = this.orderService.save(order);
//	        List<OrderProduct> orderProducts = new ArrayList<>();
//	        for (OrderProductDto dto : productOrders) {
//	            orderProducts.add(orderProductService.save(new OrderProduct(order,productService.findById(dto.getProduct().getId()).get(),dto.getQuantity())));
//	        }
//
//	        order.setOrderProducts(orderProducts);
//	        order.setDateCreated(LocalDate.now());
//		 addressRepo.save(address);
//		 order.setAddress(address);
//		 orderService.save(order);
		 orderService.insertOrder(productOrders, address, id);
		 HttpHeaders headers = new HttpHeaders();
//       headers.add("Location", uri);

       return new ResponseEntity<>(true, headers, HttpStatus.CREATED);
	 }

    
//    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
//        List<OrderProductDto> list = orderProducts
//          .stream()
//          .filter(op -> Objects.isNull(productService.findById(op
//            .getProduct()
//            .getId())))
//          .collect(Collectors.toList());
//
//        if (!CollectionUtils.isEmpty(list)) {
//            new ResourceNotFoundException("Product not found");
//        }
//    }

	 
    public static class OrderForm {

        public List<OrderProductDto> productOrders;
        public Address address;

    }
}