package com.CMD.CMD_pro.mapper;

import com.CMD.CMD_pro.domain.PostVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    public List<PostVO> selectPostList() throws Exception;
}
