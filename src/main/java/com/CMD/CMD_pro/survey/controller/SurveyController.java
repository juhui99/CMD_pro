package com.CMD.CMD_pro.survey.controller;

import com.CMD.CMD_pro.survey.domain.SurveyItemVO;
import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.domain.SurveyWithItemVO;
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

    @RequestMapping(value = "/survey")
    public String main(HttpServletRequest request, Model model) throws Exception{
        UserVO user = (UserVO) request.getAttribute("UserVO");

        if (user == null){
            return "redirect:/login";//로그인 위치
        }
        List<SurveyVO> surveyList = surveyService.getSurveyList();
        model.addAttribute("surveyList",surveyList);

        return "index";
    }

    @RequestMapping(value = "/readSurvey")
    public String readSurvey(@RequestParam("surveyIndex") int surveyIndex, @RequestParam("progressing") int progressing, Model model) throws Exception{
        boolean isProgressing = progressing == 1 ? true : false;
        SurveyVO surveyVo = null;
        if(isProgressing) {
            surveyVo = surveyService.getSurveyItems(surveyIndex);
            model.addAttribute("survey", surveyVo);
            return "readSurvey_on";
        }
        else{
            surveyVo = surveyService.getSurveyResult(surveyIndex);
            List<SurveyItemVO> itemList = ((SurveyWithItemVO)surveyVo).getSurveyItemList();

            model.addAttribute("survey", surveyVo);
            model.addAttribute("itemList", itemList);
            return "readSurvey_off";
        }
    }

    @RequestMapping("closeSurvey")
    public String closeSurvey(@RequestParam("surveyIndex") int surveyIndex) {
        try {
            surveyService.closeSurvey(surveyIndex);
        } catch (Exception e) {
            return "redirect:/survey/main?surveyclose=fail";
        }

        return "redirect:/survey/main?surveyclose=success";
    }



}
