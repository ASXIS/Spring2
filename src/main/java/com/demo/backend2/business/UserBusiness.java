package com.demo.backend2.business;

import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.FileException;
import com.demo.backend2.exception.UserException;
import com.demo.backend2.mapper.UserMapper;
import com.demo.backend2.model.MRegisterRequest;
import com.demo.backend2.model.MRegisterResponse;
import com.demo.backend2.service.UserSevice;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class UserBusiness {
    
    private final UserSevice userSevice;

    private final UserMapper userMapper;
    
    public UserBusiness(UserSevice userSevice, UserMapper userMapper) {
        this.userSevice = userSevice;
        this.userMapper = userMapper;
    }


    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userSevice.create(request.getEmail(), request.getPassword(), request.getName());

        
        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws UserException {
        if (file == null) {
            //throw error
            throw FileException.fileNull();

        }
        if (file.getSize() > 1048576 * 2) {
            //throw error
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            throw FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (supportedTypes.contains(contentType)) {
            //throw error
            throw FileException.unsupported();
        }
        // TODO: upload file File Storage (AWS, etc.)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
