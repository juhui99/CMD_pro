package com.CMD.CMD_pro.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVO {
    private int bno;
    private int c_bno;
    private int c_sequence;
    private int level;
    private Date reg_date;
    private String writer;
    private String content;
    private int delete_option;
}
