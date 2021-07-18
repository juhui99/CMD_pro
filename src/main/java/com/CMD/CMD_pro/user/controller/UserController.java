package com.CMD.CMD_pro.user.controller;

import com.CMD.CMD_pro.user.domain.JoinForm;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
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
        UserVO user = new UserVO();
        user.setUserId(form.getUser_id());
        user.setUserPwd(form.getUser_pwd());
        user.setUserName(form.getUser_name());
        user.setUserAge(form.getUser_age());
        user.setUserMajor(form.getUser_major());
        user.setUserEmail(form.getUser_email());
        user.setUserGender(form.getUser_gender());
        userMapper.userJoin(user);
        return "redirect:/join";
    }

    @ResponseBody
    @RequestMapping(value = "/idChk", method = RequestMethod.POST)
    public int idChk(HttpServletRequest req) throws Exception{
        String user_id = req.getParameter("id");
        int result = userMapper.idChk(user_id);
        return result;
    }
}
