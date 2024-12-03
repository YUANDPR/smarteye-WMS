package com.yuand.smarteye.integration.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserLoginVo implements Serializable {

    private String username;

    private String password;

    private String uuid;

    private String captcha;
}
