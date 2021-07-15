package com.CMD.CMD_pro.post.controller;

import com.CMD.CMD_pro.post.domain.PostVO;
import com.CMD.CMD_pro.post.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PostController {
    @Autowired
    private PostMapper postMapper;

    /*@RequestMapping("/")
    public String post(){return "post";}*/

    @PostMapping("/post")
    //@ResponseBody
    //@RequestMapping(value="/post" , method = {RequestMethod.GET, RequestMethod.POST})
    public String main(Model model) throws Exception {

        List<PostVO> list = postMapper.selectPostList();
        log.info("주희 테스트");

        /*for(int i = 0; i < list.size(); i++){
            System.out.println("title : " + list.get(i).getPost_title());
            System.out.println("content : " + list.get(i).getPost_content());
        }*/

        model.addAttribute("list",list);
        return "post";
    }
}
