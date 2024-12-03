package com.yuand.smarteye.integration.exception;

/**
 * 自定义异常
 */
public class UserNameExistException extends RuntimeException {
    public UserNameExistException() {
        super("用户名存在");
    }
}
