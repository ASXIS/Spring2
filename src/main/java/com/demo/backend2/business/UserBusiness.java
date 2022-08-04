package com.demo.backend2.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.FileException;
import com.demo.backend2.exception.UserException;
import com.demo.backend2.mapper.UserMapper;
import com.demo.backend2.model.MLoginRequest;
import com.demo.backend2.model.MRegisterRequest;
import com.demo.backend2.model.MRegisterResponse;
import com.demo.backend2.service.TokenService;
import com.demo.backend2.service.UserSevice;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserBusiness {

    private final UserSevice userSevice;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    public UserBusiness(UserSevice userSevice, UserMapper userMapper, TokenService tokenService) {
        this.userSevice = userSevice;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public String login(MLoginRequest request) throws BaseException {
        // validate request

        // verify database
        Optional<User> opt = userSevice.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            // throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userSevice.matchPassword(request.getPassword(), user.getPassword())) {
            // throw login fail, password incorrect
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.tokenize(user);

    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userSevice.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws UserException {
        if (file == null) {
            // throw error
            throw FileException.fileNull();

        }
        if (file.getSize() > 1048576 * 2) {
            // throw error
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
            throw FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (supportedTypes.contains(contentType)) {
            // throw error
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
