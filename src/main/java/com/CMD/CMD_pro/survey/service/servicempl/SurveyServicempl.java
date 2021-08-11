package com.CMD.CMD_pro.survey.service.servicempl;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.domain.SurveyWithItemVO;
import com.CMD.CMD_pro.survey.mapper.SurveyMapper;
import com.CMD.CMD_pro.survey.service.SurveyService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("surveyService")
public class SurveyServicempl implements SurveyService {

    private static final Logger logger = LoggerFactory.getLogger(SurveyServicempl.class);

    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public SurveyVO getSurvey(int surveyIndex) throws Exception {
        logger.info("selectSurvey");
        SurveyVO surveyVO = surveyMapper.getSurvey(surveyIndex);
        return surveyVO;
    }

    @Override
    public List<SurveyVO> getSurveyList(SurveyVO surveyVO) throws Exception {
        logger.info("selectSurveyList");
        List<SurveyVO> getSurveyList = surveyMapper.selectAnQueList(surveyVO);
        return getSurveyList;
    }

    @Override
    public List<SurveyVO> getSurveyItemList(int surveyIndex) throws Exception{
        logger.info("selectSurveyItemList");
        List<SurveyVO> getSurveyItemList = surveyMapper.selectQueItemList(surveyIndex);
        return getSurveyItemList;
    }

    @Override
    public int selectApplyCnt(SurveyVO userSurvey) throws Exception {
        logger.info("ApplyCmt");
        return surveyMapper.selectApplyCnt(userSurvey);
    }

    @Override
    public void insertSurvey(SurveyVO surveyVO) {
        logger.info("addSurvey");
        surveyMapper.insertSurvey(surveyVO);
        System.out.println("addsurvey 성공");

    }


}
