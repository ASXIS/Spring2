package com.demo.backend2.repository;

import java.util.Optional;

import com.demo.backend2.entity.Social;
import com.demo.backend2.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);

}
