package com.CMD.CMD_pro.board.controller;


import com.CMD.CMD_pro.board.domain.BoardVO;
import com.CMD.CMD_pro.board.domain.CommentVO;
import com.CMD.CMD_pro.board.domain.FileVO;
import com.CMD.CMD_pro.board.mapper.BoardMapper;
import com.CMD.CMD_pro.user.domain.UserVO;
import com.CMD.CMD_pro.user.mapper.UserMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class BoardListController {
    @Autowired
    private BoardMapper boardMapper; //게시물 관련 메퍼인터페이스
    @Autowired
    private UserMapper userMapper;

    //@RequestMapping("/board") //게시물 리스트 가져오기 글목록
    //public List<BoardVO> board() throws Exception{
       // List<BoardVO> board = boardMapper.boardList(); //게시물 메퍼에 boardList 메소드 실행후 결과 담기
       // return board;
    //}

    @GetMapping("/board")  //게시물 리스트 가져오기 get방식
    public String list(HttpServletRequest request, Model model, @RequestParam("kind") String kind, @RequestParam("realm") String realm) throws Exception{
        String pageNumber = "1";
        int off = 0;
        int target2 = 0; //새로
        if(request.getParameter("pageNumber") != null){
            pageNumber = request.getParameter("pageNumber");
        }
        if(Integer.parseInt(pageNumber) == 1){
            off = 0;
        }
        else{
            off = (Integer.parseInt(pageNumber) - 1) * 20;
        }
        List<BoardVO> board = boardMapper.boardList(off, kind, realm);

        int target =(Integer.parseInt(pageNumber) - 1) * 10;
        int target1 = boardMapper.targetPage(target);
        target1 = target1 / 10;
        int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
        if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
        int page = Integer.parseInt(pageNumber);
        int count = boardMapper.boardCount(kind,realm);
        if((count - (page * 20)) % 10 != 0 && count - (page * 20) > 0 ){ //새로 매앞에꺼만 20고침
            target2 = ((count - (page * 20)) / 20) + 1; //고침
        } else if((count - (page * 20)) % 10 == 0 && count - (page * 20) > 0) { //새로
            if((count -(page * 20)) % 20 > 1){
                target2 = ((count - (page * 20)) / 20) + 1;
            }
            else {
                target2 = ((count - (page * 20)) / 20); //고침
            }
        } else {
            target2 = 0;
        }
        int resultCount = count - (page * 20); //현재페이지 이후부터의 남은 게시물 갯수
        List<BoardVO> hotTopic = boardMapper.HotTopicList(kind,realm);
        List<String> hotContentList = new ArrayList<String>();
        for(int i=0; i < hotTopic.size(); i ++){
            String hotContent = hotTopic.get(i).getContent();
            if(hotContent.length() > 55){
                hotContent = hotContent.substring(0,55);
            }
            hotContentList.add(hotContent);
        }
        model.addAttribute("board", board); //게시물 메퍼에 boardList 로 얻은 결과를 리스트로 담아서 boardList.html로 전달
        model.addAttribute("target", target1);
        model.addAttribute("startPage", startPage);
        model.addAttribute("page", page);
        model.addAttribute("count", resultCount);
        model.addAttribute("target2", target2);
        model.addAttribute("kind",kind); //새로
        model.addAttribute("realm",realm);//새로
        if(hotTopic.size() >= 1) model.addAttribute("hotTopic1",hotTopic.get(0));
        else model.addAttribute("hotTopic1","non");
        if(hotTopic.size() >= 2) model.addAttribute("hotTopic2",hotTopic.get(1));
        else model.addAttribute("hotTopic2","non");
        if(hotTopic.size() >= 3) model.addAttribute("hotTopic3",hotTopic.get(2));
        else model.addAttribute("hotTopic3","non");
        if(hotTopic.size() >= 4) model.addAttribute("hotTopic4",hotTopic.get(3));
        else model.addAttribute("hotTopic4","non");
        if(hotTopic.size() >= 5) model.addAttribute("hotTopic5",hotTopic.get(4));
        else model.addAttribute("hotTopic5","non");
        if(hotContentList.size() >=1) model.addAttribute("hotContent1", hotContentList.get(0));
        if(hotContentList.size() >=2) model.addAttribute("hotContent2", hotContentList.get(1));
        if(hotContentList.size() >=3) model.addAttribute("hotContent3", hotContentList.get(2));
        if(hotContentList.size() >=4) model.addAttribute("hotContent4", hotContentList.get(3));
        if(hotContentList.size() >=5) model.addAttribute("hotContent5", hotContentList.get(4));
        return "boardList";
    }

    @PostMapping("board")   //게시물 리스트 가져오기 post방식 글쓰기 완료후 폼 액션
    public String WriteAction(WriteForm form, RedirectAttributes redirect, HttpSession session, Model model, @RequestPart MultipartFile files, HttpServletRequest request) throws Exception{
        BoardVO board = new BoardVO();
        board.setWriter(form.getWriter());  //BoardVO형 도메인 모델에 폼 데이터 담기
        board.setSubject(form.getSubject());
        board.setContent(form.getContent());
        String notice = form.getNotice();
        int board_bno = form.getBno();
        String userID = (String) session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        if(notice == null){
            board.setNotice(0);
        } else{
            if(user.getUser_manager()==1){
                board.setNotice(Integer.parseInt(notice));
            }
            else{
              model.addAttribute("msg","관리자 권한이 없습니다.");
              model.addAttribute("url","board?kind="+form.getKind()+"&realm="+form.getRealm());
              return "alert";
            }
        }
        board.setKind(form.getKind());
        board.setRealm(form.getRealm());

        boardMapper.boardInsert(board);

            String fileName = files.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            String fileUrl = "C:\\test1\\";
            do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);

            } while (destinationFile.exists());
            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);
        FileVO file = new FileVO();
        file.setBno(board_bno);
        file.setFilename(destinationFileName);
        file.setFileoriginname(fileName);
        file.setFileurl(fileUrl);
        boardMapper.fileInsert(file);

        redirect.addAttribute("kind", form.getKind());
        redirect.addAttribute("realm", form.getRealm());
        return "redirect:/board";
    }
    @GetMapping("/write")                  //글쓰기
    public String write(@RequestParam("kind") String kind, @RequestParam("realm") String realm, Model model, HttpSession session) throws Exception{
        String id = (String) session.getAttribute("id");
        if(id == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login"); //메세지와 url을 모델로 담아 알림창을 띄울 alert.html로 전달
            return "alert";
        }
        UserVO user = userMapper.userLogin(id);
        int maxBno = boardMapper.selectMax() + 1;
        model.addAttribute("user",user);
        model.addAttribute("kind", kind);
        model.addAttribute("realm",realm);
        model.addAttribute("maxBno", maxBno);
        return "write";
    }

    @GetMapping("/view")    //글 상세보기
    public String BoardView(@RequestParam("bno") int bno, @RequestParam("kind") String kind, @RequestParam("realm") String realm, Model model, HttpSession session,HttpServletRequest request) throws Exception{
        String userID = (String)session.getAttribute("id");
        String pageNumber = null;
        pageNumber = request.getParameter("pageNumber");
        if(userID == null){
            model.addAttribute("msg","로그인이 되어있지 않습니다.");
            model.addAttribute("url","login"); //메세지와 url을 모델로 담아 알림창을 띄울 alert.html로 전달
            return "alert";
        }
        boardMapper.boardHit(bno); //게시물 메퍼의 해당 게시물 조회수 증가 메소드 실행
        BoardVO board = boardMapper.boardView(bno,kind,realm); //파라미터로 받은 bno를 담아 게시물 메퍼의 boardView 메소드 실행된 결과를 board에 저장
        int recommendCheck = boardMapper.recommendCheck(kind,realm,bno,userID);
        UserVO user = userMapper.userLogin(userID);
        model.addAttribute("user",user);
        model.addAttribute("recommendCheck",recommendCheck);
        model.addAttribute("board", board); //저장된 board 객체를 모델로 담아 view.html로 전달
        if(boardMapper.fileDetail(bno) != null){
            model.addAttribute("file", boardMapper.fileDetail(bno));
            model.addAttribute("fileExistence","yes");
        }
        else {
            FileVO file = new FileVO();
            file.setFileoriginname("no");
            model.addAttribute("file",file);
            model.addAttribute("fileExistence","no");
        }

        List<CommentVO> commentList = boardMapper.CommentList(bno);
        int commentCount = boardMapper.CommentCount(bno);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("commentList",commentList);
        model.addAttribute("page",pageNumber);
        model.addAttribute("kind",kind);
        model.addAttribute("realm",realm);

        return "view";

    }

    @PostMapping("/update")    //글 수정
    public String Update(UpdateForm form, Model model, HttpSession session) throws Exception{
        String userID = (String)session.getAttribute("id");
        String filename = null;
        if(!userID.equals(form.getWriter())){
            model.addAttribute("msg","접근할 수 없습니다.");
            model.addAttribute("url","board?kind="+form.getKind()+"&realm="+form.getRealm());
            return "alert";
        }
        BoardVO board = new BoardVO();
        board.setBno(form.getBno()); //BoardVO형 도메인 모델에 폼 데이터 담기
        board.setSubject(form.getSubject());
        board.setContent(form.getContent());
        board.setKind(form.getKind());
        board.setRealm(form.getRealm());
        filename = form.getFilename();
        if(form.getFilename()==""){
            filename = "no";
        }
        model.addAttribute("board",board); //저장된 board 객체를 모델로 담아 update.html로 전달
        model.addAttribute("filename",filename);
        return "update";
    }

    @PostMapping("/UpdateAction")    //글 수정 폼 액션
    public String UpdateAction(UpdateForm form, RedirectAttributes redirect, @RequestPart MultipartFile files) throws Exception{
        BoardVO board = new BoardVO();
        board.setBno(form.getBno()); //BoardVO형 도메인 모델에 폼 데이터 담기
        board.setSubject(form.getSubject());
        board.setContent(form.getContent());
        board.setKind(form.getKind());
        board.setRealm(form.getRealm());
        boardMapper.boardUpdate(board); //저장된 board 객체를 파라미터로 담아 게시물 메퍼에 boardUpdate 메소드 실행

        int bno = form.getBno();
        String fileName = files.getOriginalFilename();
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\test1\\";
        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);

        } while (destinationFile.exists());
        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);
        FileVO file = new FileVO();
        file.setBno(bno);
        file.setFilename(destinationFileName);
        file.setFileoriginname(fileName);
        file.setFileurl(fileUrl);
        if(boardMapper.fileDetail(bno) != null) {
            boardMapper.fileDelete(bno);
        }
        boardMapper.fileUpdate(file);

        redirect.addAttribute("kind", form.getKind());
        redirect.addAttribute("realm", form.getRealm());
        return "redirect:/board"; //("/board")로 매핑된 get메소드 실행
    }


    @GetMapping("/delete")  //글 삭제
    public String BoardDelete(@RequestParam("bno") int bno, @RequestParam("kind") String kind, @RequestParam("realm") String realm, @RequestParam("writer") String writer, Model model, HttpSession session) throws Exception{
        String userID = (String)session.getAttribute("id");
        UserVO user = userMapper.userLogin(userID);
        if(!userID.equals(writer) && user.getUser_manager()==0){
            model.addAttribute("msg","접근할 수 없습니다.");
            model.addAttribute("url","board?kind="+kind+"&realm="+realm);
            return "alert";
        }
        boardMapper.boardDelete(bno,kind,realm,writer); //파라미터로 받은 bno를 담아 게시물 메퍼의 boardDelete메소드 실행
        boardMapper.CommentSetDelete(bno);
        model.addAttribute("msg","글을 삭제하였습니다.");
        model.addAttribute("url","board?kind="+kind+"&realm="+realm); //메세지와 url을 모델로 담아 알림창을 띄울 alert.html로 전달
        return "alert";

    }

    @PostMapping("/search_list")  //글 검색
    public String BoardSearch(HttpServletRequest request, SearchForm form, Model model) throws Exception{
        String search_option = form.getSearch_option(); //String형 변수에 폼 데이터 저장
        String keyword = form.getKeyword();
        String kind = form.getKind();
        String realm = form.getRealm();
        String pageNumber = "1";
        int off = 0;
        int target2 = 0; //새로
        if(request.getParameter("pageNumber") != null){
            pageNumber = request.getParameter("pageNumber");
        }
        if(Integer.parseInt(pageNumber) == 1){
            off = 0;
        }
        else{
            off = (Integer.parseInt(pageNumber) - 1) * 20;
        }
        int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
        if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
        int page = Integer.parseInt(pageNumber);
        int count = boardMapper.searchCount(search_option,keyword,kind,realm);
        if((count - (page * 20)) % 10 != 0 && count - (page * 20) > 0 ){ //새로 매앞에꺼만 20고침
            target2 = ((count - (page * 20)) / 20) + 1; //고침
        } else if((count - (page * 20)) % 10 == 0 && count - (page * 20) > 0) { //새로
            if((count -(page * 20)) % 20 > 1){
                target2 = ((count - (page * 20)) / 20) + 1;
            }
            else {
                target2 = ((count - (page * 20)) / 20); //고침
            }
        } else {
            target2 = 0;
        }
        int resultCount = count - (page * 20); //현재페이지 이후부터의 남은 게시물 갯수
        List<BoardVO> board = boardMapper.boardSearch(search_option, keyword,kind,realm,off); //저장된 변수들을 파라미터로 담아 boardSearch 실행후 결과를 board에 저장
        model.addAttribute("board",board); //반환된 결과인 board를 모델로 담아 search.html로 전달
        model.addAttribute("keyword",keyword); //키워드 전달
        model.addAttribute("search_option",search_option); //옵션 전달
        model.addAttribute("kind",kind);
        model.addAttribute("realm",realm);
        model.addAttribute("startPage",startPage);
        model.addAttribute("page",page);
        model.addAttribute("count",resultCount);
        model.addAttribute("target2",target2);
        return "search";
    }

    @GetMapping("/main")
    public String MainPage() throws Exception{
        return "mainpage";
    }

    @GetMapping("/search_list")  //글 검색
    public String BoardSearch2(@RequestParam("search_option") String search_option, @RequestParam("keyword") String keyword, @RequestParam("kind") String kind, @RequestParam("realm") String realm, HttpServletRequest request, SearchForm form, Model model) throws Exception{
        String pageNumber = "1";
        int off = 0;
        int target2 = 0; //새로
        if(request.getParameter("pageNumber") != null){
            pageNumber = request.getParameter("pageNumber");
        }
        if(Integer.parseInt(pageNumber) == 1){
            off = 0;
        }
        else{
            off = (Integer.parseInt(pageNumber) - 1) * 20;
        }
        int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
        if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
        int page = Integer.parseInt(pageNumber);
        int count = boardMapper.searchCount(search_option,keyword,kind,realm);
        if((count - (page * 20)) % 10 != 0 && count - (page * 20) > 0 ){ //새로 매앞에꺼만 20고침
            target2 = ((count - (page * 20)) / 20) + 1; //고침
        } else if((count - (page * 20)) % 10 == 0 && count - (page * 20) > 0) { //새로
            if((count -(page * 20)) % 20 > 1){
                target2 = ((count - (page * 20)) / 20) + 1;
            }
            else {
                target2 = ((count - (page * 20)) / 20); //고침
            }
        } else {
            target2 = 0;
        }
        int resultCount = count - (page * 20); //현재페이지 이후부터의 남은 게시물 갯수
        List<BoardVO> board = boardMapper.boardSearch(search_option, keyword,kind,realm,off); //저장된 변수들을 파라미터로 담아 boardSearch 실행후 결과를 board에 저장
        model.addAttribute("board",board); //반환된 결과인 board를 모델로 담아 search.html로 전달
        model.addAttribute("keyword",keyword); //키워드 전달
        model.addAttribute("search_option",search_option); //옵션 전달
        model.addAttribute("kind",kind);
        model.addAttribute("realm",realm);
        model.addAttribute("startPage",startPage);
        model.addAttribute("page",page);
        model.addAttribute("count",resultCount);
        model.addAttribute("target2",target2);
        return "search";
    }

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public int recommend(HttpServletRequest req) throws Exception{
        String kind = req.getParameter("kind");
        String realm = req.getParameter("realm");
        String userID = req.getParameter("id");
        int bno = Integer.parseInt(req.getParameter("bno"));
        BoardVO board = boardMapper.boardView(bno,kind,realm);
        int recommend = board.getRecommend();
        int check = boardMapper.recommendCheck(kind,realm,bno,userID);
        if(check == 0){
            boardMapper.recommendAdd(bno);
            boardMapper.recommendInsert(kind,realm,bno,userID);
            return recommend + 1;
        } else {
            boardMapper.recommendDelete(kind,realm,bno,userID);
            boardMapper.recommendSubtract(bno);
            return recommend - 1;
        }
    }

    @GetMapping("/fileDown")
    public void fileDown(@RequestParam("bno") int bno,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, Exception{
        request.setCharacterEncoding("UTF-8");
        FileVO fileVO = boardMapper.fileDetail(bno);
        try {
            String fileUrl = fileVO.getFileurl();
            System.out.println(fileUrl);
            fileUrl += "/";
            String savePath = fileUrl;
            String fileName = fileVO.getFilename();

            //실제 내보낼 파일명
            String originFileName = fileVO.getFileoriginname();
            InputStream in = null;
            OutputStream os = null;
            File file= null;
            Boolean skip = false;
            String client = "";

            //파일을 읽어 스트림에 담기
            try {
                file = new File(savePath, fileName);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }

            client = request.getHeader("User-Agent");

            //파일 다운로드 헤더 지정
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "HTML Generated Data");

            if(!skip) {
                //IE
                if(client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(originFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    //IE 11 이상
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(originFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    //한글 파일명 처리
                } else {
                    response.setHeader("Content-Disposition", "attachment; filename=\"" +
                            new String(originFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }

                response.setHeader("Content-Length", ""+file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;

                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script> alert('파일을 찾을 수 없습니다.'); history.back(); </script>");
                out.flush();
            }

            in.close();
            os.close();

        } catch (Exception e) {
            System.out.println("ERROR : " + e.getStackTrace());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
    public void fileDelete(HttpServletRequest req) throws Exception{
        int bno = Integer.parseInt(req.getParameter("bno"));
        boardMapper.fileDelete(bno);
    }

    @PostMapping("/commentInsert") //댓글등록
    public String commentInsert(CommentForm form, RedirectAttributes redirect) throws Exception{
        String pageNumber = null;
        pageNumber = form.getPageNumber();
        CommentVO comment = new CommentVO();
        comment.setBno(form.getBoard_bno());
        comment.setWriter(form.getComment_writer());
        comment.setContent(form.getCommentContent());
        boardMapper.CommentInsert(comment);
        redirect.addAttribute("bno",form.getBoard_bno());
        redirect.addAttribute("kind",form.getBoard_kind());
        redirect.addAttribute("realm",form.getBoard_realm());
        redirect.addAttribute("pageNumber",pageNumber);

       return "redirect:/view";
    }


    @PostMapping("/replyInsert") //대댓글 등록
    public String replyInsert(CommentForm form, RedirectAttributes redirect) throws Exception{
        String pageNumber = null;
        pageNumber = form.getPageNumber();
        CommentVO comment = new CommentVO();
        comment.setBno(form.getBoard_bno());
        comment.setC_sequence(form.getC_sequence());
        comment.setWriter(form.getComment_writer());
        comment.setContent(form.getCommentContent());
        boardMapper.ReplyInsert(comment);
        redirect.addAttribute("bno",form.getBoard_bno());
        redirect.addAttribute("kind",form.getBoard_kind());
        redirect.addAttribute("realm",form.getBoard_realm());
        redirect.addAttribute("pageNumber",pageNumber);
        return "redirect:/view";
    }


    @PostMapping("/commentUpdate") //댓글 대댓글수정
    public String commentUpdate(CommentForm form, RedirectAttributes redirect) throws Exception{
        String pageNumber = null;
        pageNumber = form.getPageNumber();
        int commentBno = 0;
        String commentContent = null;
        commentBno = form.getC_bno();
        commentContent = form.getCommentContent();
        boardMapper.CommentUpdate(commentBno,commentContent);
        redirect.addAttribute("bno",form.getBoard_bno());
        redirect.addAttribute("kind",form.getBoard_kind());
        redirect.addAttribute("realm",form.getBoard_realm());
        redirect.addAttribute("pageNumber",pageNumber);
        return "redirect:/view";
    }

    @GetMapping("/replyDelete")  //대댓글 삭제
    public String replyDelete(@RequestParam("bno") int bno, @RequestParam("kind") String kind, @RequestParam("realm") String realm, @RequestParam("writer") String writer,@RequestParam("commentBno") int commentBno, Model model, HttpSession session, RedirectAttributes redirect,HttpServletRequest request) throws Exception{
        String pageNumber = null;
        pageNumber = request.getParameter("pageNumber");

        String userID = (String)session.getAttribute("id");
        if(!userID.equals(writer)){
            model.addAttribute("msg","접근할 수 없습니다.");
            model.addAttribute("url","board?kind="+kind+"&realm="+realm);
            return "alert";
        }
        boardMapper.ReplyDelete(commentBno);
        redirect.addAttribute("bno",bno);
        redirect.addAttribute("kind",kind);
        redirect.addAttribute("realm",realm);
        redirect.addAttribute("pageNumber",pageNumber);
        return "redirect:/view";

    }

    @GetMapping("/commentDelete")  //댓글 삭제
    public String commentDelete(@RequestParam("bno") int bno, @RequestParam("kind") String kind, @RequestParam("realm") String realm, @RequestParam("writer") String writer,@RequestParam("commentSequence") int commentSequence, Model model, HttpSession session, RedirectAttributes redirect,HttpServletRequest request) throws Exception{
        String pageNumber = null;
        pageNumber = request.getParameter("pageNumber");
        String userID = (String)session.getAttribute("id");
        if(!userID.equals(writer)){
            model.addAttribute("msg","접근할 수 없습니다.");
            model.addAttribute("url","board?kind="+kind+"&realm="+realm);
            return "alert";
        }
        boardMapper.CommentDelete(commentSequence);
        redirect.addAttribute("bno",bno);
        redirect.addAttribute("kind",kind);
        redirect.addAttribute("realm",realm);
        redirect.addAttribute("pageNumber",pageNumber);
        return "redirect:/view";

    }


}
