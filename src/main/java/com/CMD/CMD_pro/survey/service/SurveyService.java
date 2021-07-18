package com.CMD.CMD_pro.survey.service;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    public List<SurveyVO> getSurveyList() throws Exception;
}
