package com.yuand.auth.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserLoginVo implements Serializable {

    private String username;

    private String password;

    private String uuid;

    private String captcha;

}
