package com.demo.backend2.mapper;

import com.demo.backend2.entity.User;
import com.demo.backend2.model.MRegisterResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MRegisterResponse toRegisterResponse(User user);
    
}
