package com.demo.backend2.service;

import java.util.List;

import com.demo.backend2.entity.Address;
import com.demo.backend2.entity.User;
import com.demo.backend2.repository.AddressRepository;

import org.springframework.stereotype.Service;

import lombok.Data;
@Data
@Service
public class AddressSevice {

    private final AddressRepository repository;
    
    public AddressSevice(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findByUser(User user){
        return repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode){
        //TODO: validate

        //create
        Address entity = new Address();
        
        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return repository.save(entity);
    }
    
}
