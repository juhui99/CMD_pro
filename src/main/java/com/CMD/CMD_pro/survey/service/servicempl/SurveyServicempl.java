package com.CMD.CMD_pro.survey.service.servicempl;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SurveyServicempl implements SurveyService {
    private  static final Logger logger = LoggerFactory.getLogger(SurveyServicempl.class);
    private  String namespace = "com.CMD.CMD_pro.mapper.SurveyMapper";
    @Override
    public List<SurveyVO> getSurveyList() throws Exception {
        logger.info("selectSurveyList");

//        List<SurveyVO> surveyList
        return null;
    }
}
