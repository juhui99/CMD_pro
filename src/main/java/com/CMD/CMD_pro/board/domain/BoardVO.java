package com.CMD.CMD_pro.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardVO {
    private int bno;
    private String subject;
    private String content;
    private String writer;
    private Date reg_date;
    private int hit;
    private int notice;
    private String kind;
    private String realm;
    private int recommend;
}
