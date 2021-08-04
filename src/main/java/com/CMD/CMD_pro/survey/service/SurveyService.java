package com.CMD.CMD_pro.survey.service;

import com.CMD.CMD_pro.survey.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    public List<SurveyVO> getSurveyList() throws Exception;
    //public PageMaker getPagination() throws Exception;
    public SurveyVO getSurvey(int survey_seq) throws Exception;
    public SurveyWithItemVO getSurveyItems(int survey_seq) throws Exception;
    public SurveyWithDatasetVO getSurveyResult(int survey_seq) throws Exception;
    public void addSurvey(SurveyVO svo, SurveyWithItemVO sivo);
    public void	closeSurvey(int survey_seq);
    public void removeSurvey(int survey_seq);
    public void addSurveyResult(SurveyResultVO srvo);

    public List<SurveyVO> getSearchMember();
    public void removeSurveyUnabled(String[] surseqlist, String realPath);
}
