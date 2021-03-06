package com.demo.backend2.business;

import java.util.Objects;

import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.ProductException;

import org.springframework.stereotype.Service;

@Service
public class ProductBusiness {
    public String getProductById (String id) throws BaseException {
        if (Objects.equals("1234", id)){
            throw ProductException.notFound();
        }
        return id;
    }
}
