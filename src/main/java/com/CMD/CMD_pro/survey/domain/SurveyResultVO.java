package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SurveyResultVO {
    private int surveyResultIndex;
    private int surveyIndex;
    private int surveyItemIndex;
    private String surveyResult;
    private Date surveyResultReg;
}
