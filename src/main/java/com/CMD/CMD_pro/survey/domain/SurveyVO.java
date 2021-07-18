package com.CMD.CMD_pro.survey.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SurveyVO {
    private int surveyIndex;
    private int userIndex;
    private String surveyTitle;
    private String surveyContent;
    private Date surveyReg;
    private Date surveyEnd;
    private int surveyCount;
    private int surveyDuplicate;
}
