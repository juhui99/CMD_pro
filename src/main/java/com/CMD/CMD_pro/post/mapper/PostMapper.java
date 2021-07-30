package com.CMD.CMD_pro.post.mapper;

import com.CMD.CMD_pro.post.domain.PostVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {
    public List<PostVO> selectPostList() throws Exception;

}
