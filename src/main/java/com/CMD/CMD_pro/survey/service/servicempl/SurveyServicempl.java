package com.CMD.CMD_pro.survey.service.servicempl;

import com.CMD.CMD_pro.survey.domain.SurveyVO;
import com.CMD.CMD_pro.survey.domain.SurveyWithItemVO;
import com.CMD.CMD_pro.survey.service.SurveyService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServicempl implements SurveyService {

//    @Autowired
//    SurveyVO surveyVO;
    @Autowired
    private SqlSession sqlSession;

    private  String namespace = "com.CMD.CMD_pro.mapper.SurveyMapper";

    @Override
    public List<SurveyVO> getSurveyList() throws Exception {
        List<SurveyVO> surveyList = sqlSession.selectList(namespace+".selectSurveyList");
        return surveyList;
    }

//    @Override
//    public SurveyVO getSurveyVO(int surveyIndex) throws Exception{
//        return sqlSession.selectOne(namespace+".selectSurvey", surveyIndex);
//    }

    @Override
    public SurveyWithItemVO getSurveyItems(int surveyIndex) throws Exception {
        SurveyVO surveyVO = getSurvey(surveyIndex);
        SurveyWithItemVO surveyWithItemVO = new SurveyWithItemVO(surveyVO);
       // SurveyWithItemVO.setSurveyItemList(dao.selectSurveyItems(surveyIndex));
//		surveyWithItemVO.setMySurvey(dao.selecyMySurveyResult(survey_seq, member_seq));
        return surveyWithItemVO;
    }



}
