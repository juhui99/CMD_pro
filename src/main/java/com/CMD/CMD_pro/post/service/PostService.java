package com.CMD.CMD_pro.post.service;

import com.CMD.CMD_pro.post.domain.PostVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostVO> selectPostList() throws Exception;
}
