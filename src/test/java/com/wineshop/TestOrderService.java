package com.wineshop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.wineshop.exception.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wineshop.model.Address;
import com.wineshop.model.Order;
import com.wineshop.model.OrderProduct;
import com.wineshop.model.OrderProductDto;
import com.wineshop.model.OrderProductPK;
import com.wineshop.model.User;
import com.wineshop.model.Wine;
import com.wineshop.repository.AddressRepository;
import com.wineshop.repository.OrderProductRepository;
import com.wineshop.repository.OrderRepository;
import com.wineshop.repository.UserRepository;
import com.wineshop.repository.WineRepository;
import com.wineshop.service.OrderService;

@ExtendWith(MockitoExtension.class)
class TestOrderService {
	@InjectMocks
	OrderService orderService;
	
	@Mock
	WineRepository wineRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	OrderRepository orderRepo;
	
	@Mock
	OrderProductRepository orderProductRepo;
	
	@Mock
	AddressRepository addressRepo;
	
	@Spy
	Address address;
	
	@Spy
	Order order;
	
	@Spy
	OrderProductDto orderProductDto1;
	
	@Spy
	OrderProductDto orderProductDto2;
	
	@Spy
	OrderProductDto orderProductDto3;
	
	@Spy
	Wine wine1;
	@Spy
	Wine wine2;
	@Spy
	Wine wine3;
	@Spy
	Wine wine4;
	
	@Spy 
	User user1;
	
	@Spy
	OrderProduct orderProduct;
	
	@Spy
	OrderProductPK pk;
	

	@Test
	void test() {
	wine1.setId(1l);
	wine2.setId(2l);
	wine3.setId(3l);
	orderProductDto1.setProduct(wine1);
	orderProductDto1.setQuantity(2);
	orderProductDto2.setProduct(wine2);
	orderProductDto2.setQuantity(1);
	orderProductDto3.setProduct(wine3);
	orderProductDto3.setQuantity(3);
	List<OrderProductDto> productOrders = new ArrayList<>();
	productOrders.add(orderProductDto1);
	productOrders.add(orderProductDto2);
	productOrders.add(orderProductDto3);
	when(wineRepo.findById(1l)).thenReturn(Optional.of(wine1));
	
	try {
		assertTrue(true==orderService.validateProductsExistence(productOrders));
	} catch (RecordNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
//	assertThrows(RecordNotFoundException.class,()->orderService.validateProductsExistence(productOrders));
	
	
//	address.setId(1l);
//	
//	user1.setId(1l);
////	Optional<User> o = Optional.of(user1);
//	pk.setOrder(order);
//	pk.setProduct(wine1);
//	orderProduct.setPk(pk);
//	orderProduct.setQuantity(1);
//
//	when(userRepo.findById(1l)).thenReturn(Optional.of(user1));
////	when(orderRepo.save(order)).thenReturn(order);
//	when(addressRepo.save(address)).thenReturn(address);
//	when(orderProductRepo.save(orderProduct)).thenReturn(orderProduct);
//	
//	assertEquals(3, orderService.insertOrder(productOrders, address, 1l).getOrderProducts().size());
//	
	
	
		
	}

}
