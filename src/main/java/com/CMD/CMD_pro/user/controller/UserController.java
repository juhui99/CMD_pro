package com.CMD.CMD_pro.user.controller;

import com.CMD.CMD_pro.post.domain.JoinForm;
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
        user.setUser_id(form.getUserId());
        user.setUser_pwd(form.getUserPwd());
        user.setUser_name(form.getUserName());
        user.setUser_age(form.getUserAge());
        user.setUser_major(form.getUserMajor());
        user.setUser_email(form.getUserEmail());
        user.setUser_gender(form.getUserGender());
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
