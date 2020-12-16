package com.wineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wineshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}