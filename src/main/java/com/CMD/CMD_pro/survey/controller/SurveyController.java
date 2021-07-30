package com.CMD.CMD_pro.survey.controller;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.service.SurveyService;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/survey/*") //현재 믈래스의 모든 메서드들의 기본적인 URL경로
public class SurveyController {
    @Autowired
    private UserService userService;
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/main")
    public String main(HttpServletRequest req) throws Exception{
        UserVO user = (UserVO) req.getAttribute("UserVO");

        if (user == null){
            return "redirect:/join";//로그인 위치
        }else{

        }


        return null;
    }

    //@RequestMapping(value = "/")



}
