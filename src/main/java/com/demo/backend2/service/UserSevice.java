package com.demo.backend2.service;

import java.util.Objects;

import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.UserException;
import com.demo.backend2.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    
    public UserSevice(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(String email, String password, String name) throws BaseException{
        
        //validate
        if (Objects.isNull(email)){
            // throw error
            throw UserException.CreateEmailNull();
        }

        if (Objects.isNull(password)){
            // throw error
            throw UserException.CreatePasswordNull();
        }

        if (Objects.isNull(name)){
            // throw error
            throw UserException.CreateNameNull();
        }

        //verify
        if(repository.existsByEmail(email)){
            throw UserException.CreateEmailDuplicate();
        }



        //save
        
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return repository.save(entity);
    }
    
}
