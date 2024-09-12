package com.foodie.exception;

//账号被锁定（禁用）异常
public class AccountLockedException extends BaseException{
    public AccountLockedException(String message) {
        super(message);
    }
}
