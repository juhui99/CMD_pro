package com.CMD.CMD_pro.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    private String userId;
    private String userPwd;
    private String userName;
    private int userAge;
    private String userMajor;
    private String userEmail;
    private String userGender;
}