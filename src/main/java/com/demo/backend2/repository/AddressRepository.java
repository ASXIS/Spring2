package com.demo.backend2.repository;

import java.util.List;

import com.demo.backend2.entity.Address;
import com.demo.backend2.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);

}
