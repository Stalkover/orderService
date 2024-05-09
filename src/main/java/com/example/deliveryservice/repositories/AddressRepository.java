package com.example.deliveryservice.repositories;

import com.example.deliveryservice.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
