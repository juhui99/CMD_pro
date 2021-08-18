package com.CMD.CMD_pro.survey.service.servicempl;

import com.CMD.CMD_pro.survey.domain.*;
import com.CMD.CMD_pro.survey.mapper.SurveyMapper;
import com.CMD.CMD_pro.survey.service.SurveyService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service("surveyService")
public class SurveyServicempl implements SurveyService {

    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public SurveyVO getSurvey(int surveyIndex) throws Exception {
        return surveyMapper.selectSurvey(surveyIndex);
    }

    @Override
    public List<SurveyVO> getSurveyList(SearchCriteria cri) throws Exception {
        return surveyMapper.selectSurveyList(cri);
    }

    @Override
    public PageMaker getPagination(SearchCriteria cri) throws Exception {
        return surveyMapper.selectCountPaging(cri);
    }

    @Override
    public SurveyWithItemVO getSurveyItems(int surveyIndex) throws Exception {
        SurveyVO surveyVO = getSurvey(surveyIndex);
        SurveyWithItemVO surveyWithItemVO = new SurveyWithItemVO(surveyVO);
        surveyWithItemVO.setSurveyItemList(surveyMapper.selectSurveyItems(surveyIndex));
//		surveyWithItemVO.setMySurvey(dao.selecyMySurveyResult(survey_seq, member_seq));
        return surveyWithItemVO;
    }

    @Override
    public SurveyWithDatasetVO getSurveyResult(int survey_seq) throws Exception {
        SurveyWithItemVO surveyWithItemVO = getSurveyItems(survey_seq);
        SurveyWithDatasetVO surveyWithDatasetVO = new SurveyWithDatasetVO(surveyWithItemVO);
        List<ResultDataSet> dataSetList = surveyMapper.selectSurveyResultDataSet(survey_seq);
        surveyWithDatasetVO.setDataset(dataSetList);
        return surveyWithDatasetVO;
    }
    // 설문조사 보기 선택
    @Override
    public void addSurveyResult(SurveyResultVO srvo) {
        surveyMapper.addSurveyResult(srvo);
    }

    @Override
    public List<SurveyVO> getSearchMember(SearchCriteria cri) {
        List<SurveyVO> list = surveyMapper.selectSearchSurvey(cri);
        return list;
    }
    @Override
    public void closeSurvey(int survey_seq) {
        surveyMapper.closeSurvey(survey_seq);
    }

    @Override
    public void removeSurvey(int survey_seq) {
        surveyMapper.removeSurvey(survey_seq);
    }


//    @Override
//    public List<SurveyVO> getSurveyItemList(int surveyIndex) throws Exception{
//        logger.info("selectSurveyItemList");
//        List<SurveyVO> getSurveyItemList = surveyMapper.selectQueItemList(surveyIndex);
//        return getSurveyItemList;
//    }
//
//    @Override
//    public int selectApplyCnt(SurveyVO userSurvey) throws Exception {
//        logger.info("ApplyCmt");
//        return surveyMapper.selectApplyCnt(userSurvey);
//    }
//
//    @Override
//    public void insertSurvey(SurveyVO surveyVO) {
//        logger.info("addSurvey");
//        surveyMapper.insertSurvey(surveyVO);
//        System.out.println("addsurvey 성공");
//
//    }


}
