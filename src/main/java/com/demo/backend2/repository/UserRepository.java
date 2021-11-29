package com.demo.backend2.repository;

import java.util.Optional;

import com.demo.backend2.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    boolean existsByName(String name);

}
