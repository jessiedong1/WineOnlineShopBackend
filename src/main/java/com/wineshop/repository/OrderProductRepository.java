package com.wineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wineshop.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}