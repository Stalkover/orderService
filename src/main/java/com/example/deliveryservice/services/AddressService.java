package com.example.deliveryservice.services;

import com.example.deliveryservice.entities.Address;
import com.example.deliveryservice.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void save(Address address){addressRepository.save(address);}

    @Transactional
    public Address createAddress(Double latitude, Double longitude, String stringAddress){
        Address address = new Address();
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        address.setAddress(stringAddress);
        addressRepository.save(address);
        return address;
    }
}
