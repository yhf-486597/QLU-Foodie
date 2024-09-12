package com.foodie.exception;

//无法删除异常
public class DeletionNotAllowedException extends BaseException{

    public DeletionNotAllowedException(String message) {
        super(message);
    }

}
