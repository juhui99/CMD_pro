package com.CMD.CMD_pro.survey.controller;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.service.SurveyService;
import com.CMD.CMD_pro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/survey/*") //현재 믈래스의 모든 메서드들의 기본적인 URL경로
public class SurveyController {
    @Autowired
    private UserService userService;
    @Autowired
    private SurveyService surveyService;

//    @GetMapping("/main")
//    public String main(Model model) throws Exception{
//        List<SurveyVO> surveyList;
//
//    }



}
