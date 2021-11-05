package com.demo.backend2.exception;

public abstract class BaseException extends Exception{
    public BaseException(String code){
        super(code);
    }
}
