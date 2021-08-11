package com.CMD.CMD_pro.survey.service;

import com.CMD.CMD_pro.survey.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    List<SurveyVO> getSurveyList(SurveyVO surveyVO) throws Exception;
    List<SurveyVO> getSurveyItemList(int surveyIndex) throws Exception;
    int selectApplyCnt(SurveyVO userSurvey) throws Exception;

    //public PageMaker getPagination() throws Exception;
    public SurveyVO getSurvey(int surveyIndex) throws Exception;
//    public SurveyWithItemVO getSurveyItems(int survey_seq) throws Exception;
//    public SurveyWithDatasetVO getSurveyResult(int survey_seq) throws Exception;
    public void insertSurvey(SurveyVO surveyVO);
//    public void	closeSurvey(int survey_seq);
//    public void removeSurvey(int survey_seq);
//    public void addSurveyResult(SurveyResultVO srvo);
//
//    public List<SurveyVO> getSearchMember();
//    public void removeSurveyUnabled(String[] surseqlist, String realPath);
}
