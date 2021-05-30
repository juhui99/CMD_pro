package com.CMD.CMD_pro.mapper;

import com.CMD.CMD_pro.domain.PostVO;

import java.util.List;

public interface PostMapper {

    public List<PostVO> selectPostList() throws Exception;
}
