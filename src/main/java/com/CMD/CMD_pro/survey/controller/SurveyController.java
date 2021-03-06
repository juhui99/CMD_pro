package com.CMD.CMD_pro.survey.controller;

import com.CMD.CMD_pro.survey.domain.*;
import com.CMD.CMD_pro.survey.service.SurveyService;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/survey/*") //현재 믈래스의 모든 메서드들의 기본적인 URL경로
public class SurveyController {

//    @Autowired
//    private SurveyVO surveyVO;
//    @Autowired
//    private UserService userService;
    @Autowired
    private SurveyService surveyService;

    @RequestMapping("main")
    public String main(
            @ModelAttribute("cri") SearchCriteria cri,
            Model model
    ) throws Exception {

        List<SurveyVO> surveyList = surveyService.getSurveyList(cri);
        model.addAttribute("surveyList", surveyList);

        PageMaker pageMaker = surveyService.getPagination(cri);
        model.addAttribute("pageMaker", pageMaker);

        return "index";
    }

//    @RequestMapping(value = "/mainsurvey")
//    public String main(HttpServletRequest request, Model model) throws Exception{
//        UserVO user = (UserVO) request.getAttribute("UserVO");
//
//        if (user == null){
//            return "redirect:/login";//로그인 위치
//        }
//        List<SurveyVO> surveyList = surveyService.getSurveyList();
//        model.addAttribute("surveyList",surveyList);
//
//        return "index";
//    }

//    @RequestMapping(value = "/surveyView")
//    public String surveyView(HttpServletRequest request, SurveyVO surveyVO, Model model)throws Exception {
//        SurveyVO survey = surveyService.getSurvey(surveyVO.getSurveyIndex());
//        request.setCharacterEncoding("UTF-8");
//
//        SurveyVO useSurvey = new SurveyVO();
//        useSurvey.setUserIndex(surveyVO.getUserIndex());
//        useSurvey.setSurveyIndex(surveyVO.getSurveyIndex());
//
//        List<SurveyVO> surveyVOList = surveyService.getSurveyList(survey);
//        //int anQuestionResultCnt = surveyService.selectAnQueListCnt(survey);
//
//        //설문조사 답변 항목 가져오기
//        List<SurveyVO> surveyItemList = surveyService.getSurveyItemList(surveyVO.getSurveyIndex());
//
//        //설문조사 참여인원 가져오기
//        int applyCnt = surveyService.selectApplyCnt(useSurvey);
//
////        model.addAttribute("queListCnt", anQuestionResultCnt);
//        model.addAttribute("surveyList", surveyVOList);
//        model.addAttribute("surveyItemList", surveyItemList);
//        model.addAttribute("surveyIndex", surveyVO.getSurveyIndex());
//        model.addAttribute("survey", survey);
//        model.addAttribute("applyCnt", applyCnt);
//
//        model.addAttribute("name", "survey");
//        return "redirect:/surveyView";
//    }

    @RequestMapping(value = "/readSurvey")
    public String readSurvey(@RequestParam("surveyIndex") int surveyIndex, @RequestParam("progressing") int progressing, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
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

    @RequestMapping(value="addSurvey")
    public String addSurvey(@RequestParam("title") String surveyTitle, @RequestParam("content") String surveyContent, @RequestParam("itemcontent") String [] itemcontent, @RequestParam("end_date") String surveyEnd, HttpServletRequest request, Model model) throws Exception {
        SurveyVO surveyVO = new SurveyVO();
        SurveyWithItemVO surveyWithItemVO = new SurveyWithItemVO();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        surveyVO.setSurveyEnd(sdf.parse(surveyEnd));
        surveyVO.setSurveyTitle(surveyTitle);
        surveyVO.setSurveyContent(surveyContent);
        surveyVO.setUserIndex(surveyVO.getUserIndex());
        List<SurveyItemVO> surveyItemList = new ArrayList<>();
        for (int i = 0; i < itemcontent.length; i++) {
            SurveyItemVO temp  = new SurveyItemVO();
            temp.setSurveyItemContent(itemcontent[i]);
            surveyItemList.add(temp);
        }
        surveyWithItemVO.setSurveyItemList(surveyItemList);
        surveyService.addSurvey(surveyVO,surveyWithItemVO);

        return "redirect:/survey/main";
    }

    @RequestMapping(value="voteSurvey", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addSurveyResult(
            @RequestParam("itemSeq") int itemSeq,
            @RequestParam("survey_seq") int surveySeq) {
        SurveyResultVO srvo = new SurveyResultVO();
        Map<String, Object> return_param = new HashMap<>();
        try {
            srvo.setSurveyResultIndex(itemSeq);
            //srvo.setMember_seq(user.getMember_seq());
            srvo.setSurveyIndex(surveySeq);
            surveyService.addSurveyResult(srvo);
            return_param.put("result", true);
            return_param.put("message", "설문에 참여하였습니다.");
        } catch (Exception e) {
            return_param.put("result", false);
            return_param.put("message", "이미 설문에 참여하였습니다.");
            return return_param;
        }

        return return_param;
    }

    @RequestMapping("removeSurvey")
    public String removeSurvey(@RequestParam("surveyIndex") int surveyIndex) {
        try {
            surveyService.removeSurvey(surveyIndex);
        } catch (Exception e) {
            return "redirect:/survey/main?surveyremove=fail";
        }

        return "redirect:/survey/main?surveyremove=success";
    }



}
