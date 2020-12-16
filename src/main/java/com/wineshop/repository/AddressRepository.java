package com.wineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wineshop.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
