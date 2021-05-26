package com.CMD.CMD_pro.controller;

import com.CMD.CMD_pro.domain.userVO;
import com.CMD.CMD_pro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/JoinAction")
    public String joinAction(JoinForm form) throws Exception{
        userVO user = new userVO();
        user.setUser_id(form.getUser_id());
        user.setUser_pwd(form.getUser_pwd());
        user.setUser_name(form.getUser_name());
        user.setUser_age(form.getUser_age());
        user.setUser_major(form.getUser_major());
        user.setUser_email(form.getUser_email());
        user.setUser_gender(form.getUser_gender());
        userMapper.userJoin(user);
        return "redirect:/join";
    }
}
