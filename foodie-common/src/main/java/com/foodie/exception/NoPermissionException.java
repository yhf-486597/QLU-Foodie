package com.foodie.exception;

//无TOP权限异常
public class NoPermissionException extends BaseException{

    public NoPermissionException(String message) {
        super(message);

    }
}

