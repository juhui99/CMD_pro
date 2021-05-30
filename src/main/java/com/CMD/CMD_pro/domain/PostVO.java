package com.CMD.CMD_pro.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class PostVO {
    private int post_index;
    private int board_index;
    private int user_index;
    private String user_id;
    private String user_name;
    private String post_title;
    private String post_content;
    private Date post_regdate;
    private Boolean is_secret;
    private Boolean post_del;
    private int post_hit;
    private int post_like;
    private String post_file;

}
