package com.CMD.CMD_pro.user.controller;

import com.CMD.CMD_pro.user.domain.JoinForm;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
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
        user.setUserId(form.getUserId());
        user.setUserPwd(form.getUserPwd());
        user.setUserName(form.getUserName());
        user.setUserAge(form.getUserAge());
        user.setUserMajor(form.getUserMajor());
        user.setUserEmail(form.getUserEmail());
        user.setUserGender(form.getUserGender());
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
