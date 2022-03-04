package com.demo.backend2.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user." + code);
    }
    public static UserException requestNull(){
        return new UserException("register.email.null");
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    // Create

    public static UserException CreateEmailDuplicate(){
        return new UserException("create.email.Duplicate");
    }
    public static UserException CreateEmailNull(){
        return new UserException("create.email.null");
    }

    public static UserException CreatePasswordNull(){
        return new UserException("create.password.null");
    }

    public static UserException CreateNameNull(){
        return new UserException("create.name.null");
    }

    // Login

    public static UserException loginFailEmailNotFound(){
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect(){
        return new UserException("login.fail");
    }

}
