package com.CMD.CMD_pro.post.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class PostVO {
    private int postIndex;
    private int boardIndex;
    private int userIndex;
    private String userId;
    private String userName;
    private String postTitle;
    private String postContent;
    private Date postRegdate;
    private Boolean isSecret;
    private Boolean postDel;
    private int postHit;
    private int postLike;
    private String postFile;

}
