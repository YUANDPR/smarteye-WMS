package com.yuand.smarteye.integration.service;

public interface MobileService {
    //登录，成功返回0，失败返回1
    int login(String username, String password, Integer loginType);
}
