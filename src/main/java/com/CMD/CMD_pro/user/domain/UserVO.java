package com.CMD.CMD_pro.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private int userIndex;
    private String user_id;
    private String user_pwd;
    private String userName;
    private int userAge;
    private String userMajor;
    private String userEmail;
    private String userGender;
    private String userProfile;
    private int userManager;
}
